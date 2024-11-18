package com.littlepony.PodcastService.utils.command_line_runners;

import com.littlepony.PodcastService.models.UserType;
import com.littlepony.PodcastService.models.dtos.*;
import com.littlepony.PodcastService.services.*;
import com.littlepony.PodcastService.utils.exeptions.InvalidPodcastDataException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

@Component
public class ApplicationCommandLineRunner implements CommandLineRunner {

    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private final ConfigurableApplicationContext context;
    private final UserService userService;
    private final PodcastService podcastService;
    private final EpisodeService episodeService;
    private final CategoryService categoryService;
    private final PlaylistService playlistService;

    public ApplicationCommandLineRunner(ConfigurableApplicationContext context,
                                        UserService userService,
                                        PodcastService podcastService,
                                        EpisodeService episodeService,
                                        CategoryService categoryService,
                                        PlaylistService playlistService) {
        this.context = context;
        this.userService = userService;
        this.podcastService = podcastService;
        this.episodeService = episodeService;
        this.categoryService = categoryService;
        this.playlistService = playlistService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Добро пожаловать в Podcast Service!");

        while (true) {
            System.out.println("Выберите действие:" +
                    "\n1 - Добавить пользователя" +
                    "\n2 - Посмотреть пользователей" +
                    "\n3 - Добавить подкаст" +
                    "\n4 - Посмотреть подкасты" +
                    "\n5 - Добавить эпизод" +
                    "\n6 - Посмотреть эпизоды подкаста" +
                    "\n7 - Добавить категорию" +
                    "\n8 - Посмотреть категории" +
                    "\n9 - Создать плейлист" +
                    "\n10 - Добавить эпизод в плейлист" +
                    "\n11 - Посмотреть плейлисты пользователя" +
                    "\n12 - Подписаться на подкаст" +
                    "\n12 - Подписаться на подкаст" +
                    "\n13 - Отписаться от подкаста" +
                    "\n14 - Просмотреть подписки" +
                    "\n0 - Выйти");

            String input = bufferedReader.readLine();

            switch (input) {
                case "1" -> addUser();
                case "2" -> viewAllUsers();
                case "3" -> addPodcast();
                case "4" -> viewAllPodcasts();
                case "5" -> addEpisode();
                case "6" -> viewPodcastEpisodes();
                case "7" -> addCategory();
                case "8" -> viewAllCategories();
                case "9" -> createPlaylist();
                case "10" -> addEpisodeToPlaylist();
                case "11" -> viewUserPlaylists();
                case "12" -> subscribeToPodcast();
                case "13" -> unsubscribeFromPodcast();
                case "14" -> viewSubscriptions();
                case "0" -> {
                    context.close();
                    return;
                }
                default -> System.out.println("Неверная команда");
            }
            System.out.println("==================================");
        }
    }

    private void addUser() throws IOException {
        System.out.println("Введите данные пользователя в формате: имя email пароль тип(LISTENER/CREATOR)");
        String[] userParams = bufferedReader.readLine().split("\\s+");

        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(userParams[0]);
            userDTO.setEmail(userParams[1]);
            userDTO.setPassword(userParams[2]);
            userDTO.setType(UserType.valueOf(userParams[3].toUpperCase()));

            userService.create(userDTO);
            System.out.println("Пользователь успешно добавлен");
        } catch (Exception e) {
            System.out.println("Ошибка добавления пользователя: " + e.getMessage());
        }
    }

    private void viewAllUsers() {
        List<UserDTO> users = userService.findAll();
        users.forEach(user -> System.out.printf("Пользователь: %s, Email: %s, Тип: %s%n",
                user.getName(), user.getEmail(), user.getType()));
    }

    private void addPodcast() throws IOException {
        System.out.println("Введите данные подкаста в формате: название описание категория создатель_email язык");
        String[] podcastParams = bufferedReader.readLine().split("\\s+");

        try {
            PodcastDTO podcastDTO = new PodcastDTO();
            podcastDTO.setTitle(podcastParams[0]);
            podcastDTO.setDescription(podcastParams[1]);
            podcastDTO.setCategoryName(podcastParams[2]);
            podcastDTO.setCreatorName(podcastParams[3]);
            podcastDTO.setLanguage(podcastParams[4]);

            podcastService.create(podcastDTO);
            System.out.println("Подкаст успешно создан");
        } catch (InvalidPodcastDataException e) {
            System.out.println("Ошибка в данных подкаста: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла неожиданная ошибка: " + e.getMessage());
        }
    }

    private void viewAllPodcasts() {
        List<PodcastDTO> podcasts = podcastService.findAll();
        podcasts.forEach(podcast -> System.out.printf(
                "Подкаст: %s, Создатель: %s, Категория: %s, Рейтинг: %.1f%n",
                podcast.getTitle(), podcast.getCreatorName(),
                podcast.getCategoryName(), podcast.getRating()));
    }

    private void addEpisode() throws IOException {
        System.out.println("Введите данные эпизода в формате: название подкаст_uuid описание длительность(в минутах)");
        String[] episodeParams = bufferedReader.readLine().split("\\s+");

        try {
            EpisodeDTO episodeDTO = new EpisodeDTO();
            episodeDTO.setTitle(episodeParams[0]);
            episodeDTO.setDescription(episodeParams[2]);
            episodeDTO.setDuration(Integer.parseInt(episodeParams[3]));

            episodeService.create(episodeDTO);
            System.out.println("Эпизод успешно добавлен");
        } catch (Exception e) {
            System.out.println("Ошибка добавления эпизода: " + e.getMessage());
        }
    }

    private void viewPodcastEpisodes() throws IOException {
        System.out.println("Введите UUID подкаста:");
        String podcastId = bufferedReader.readLine();

        try {
            List<EpisodeDTO> episodes = episodeService.findByPodcast(UUID.fromString(podcastId));
            episodes.forEach(episode -> System.out.printf(
                    "Эпизод: %s, Длительность: %d минут, Прослушиваний: %d%n",
                    episode.getTitle(), episode.getDuration(), episode.getListenCount()));
        } catch (Exception e) {
            System.out.println("Ошибка просмотра эпизодов: " + e.getMessage());
        }
    }

    private void addCategory() throws IOException {
        System.out.println("Введите данные категории в формате: название описание иконка_url");
        String[] categoryParams = bufferedReader.readLine().split("\\s+");

        try {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setName(categoryParams[0]);
            categoryDTO.setDescription(categoryParams[1]);
            categoryDTO.setIconUrl(categoryParams[2]);

            categoryService.create(categoryDTO);
            System.out.println("Категория успешно добавлена");
        } catch (Exception e) {
            System.out.println("Ошибка добавления категории: " + e.getMessage());
        }
    }

    private void viewAllCategories() {
        List<CategoryDTO> categories = categoryService.findAll();
        categories.forEach(category -> System.out.printf(
                "Категория: %s, Описание: %s%n",
                category.getName(), category.getDescription()));
    }

    private void createPlaylist() throws IOException {
        System.out.println("Введите данные плейлиста в формате: название описание пользователь_uuid публичный(true/false)");
        String[] playlistParams = bufferedReader.readLine().split("\\s+");

        try {
            PlaylistDTO playlistDTO = new PlaylistDTO();
            playlistDTO.setName(playlistParams[0]);
            playlistDTO.setDescription(playlistParams[1]);
            playlistDTO.setUserId(UUID.fromString(playlistParams[2]));

            playlistService.create(playlistDTO);
            System.out.println("Плейлист успешно создан");
        } catch (Exception e) {
            System.out.println("Ошибка создания плейлиста: " + e.getMessage());
        }
    }

    private void addEpisodeToPlaylist() throws IOException {
        System.out.println("Введите UUID плейлиста:");
        String playlistId = bufferedReader.readLine();

        System.out.println("Введите UUID эпизода:");
        String episodeId = bufferedReader.readLine();

        try {
            playlistService.addEpisodeToPlaylist(
                    UUID.fromString(playlistId),
                    UUID.fromString(episodeId));
            System.out.println("Эпизод успешно добавлен в плейлист");
        } catch (Exception e) {
            System.out.println("Ошибка добавления эпизода в плейлист: " + e.getMessage());
        }
    }

    private void viewUserPlaylists() throws IOException {
        System.out.println("Введите UUID пользователя:");
        String userId = bufferedReader.readLine();

        try {
            List<PlaylistDTO> playlists = playlistService.findByUser(UUID.fromString(userId));
            playlists.forEach(playlist -> {
                System.out.printf("Плейлист: %s, Описание: %s",
                        playlist.getName(), playlist.getDescription());
                System.out.println("Эпизоды в плейлисте:");
                playlist.getEpisodes().forEach(episode ->
                        System.out.printf("- %s (%d мин.)%n",
                                episode.getTitle(), episode.getDuration()));
            });
        } catch (Exception e) {
            System.out.println("Ошибка просмотра плейлистов: " + e.getMessage());
        }
    }

    private void unsubscribeFromPodcast() throws IOException {
        System.out.println("Введите UUID пользователя:");
        String userId = bufferedReader.readLine();

        System.out.println("Текущие подписки:");
        List<PodcastDTO> subscriptions = userService.getUserSubscriptions(UUID.fromString(userId));
        subscriptions.forEach(podcast ->
                System.out.printf("%s - %s%n",
                        podcast.getId(),
                        podcast.getTitle())
        );

        System.out.println("Введите UUID подкаста для отписки:");
        String podcastId = bufferedReader.readLine();

        try {
            userService.unsubscribeFromPodcast(
                    UUID.fromString(userId),
                    UUID.fromString(podcastId)
            );
            System.out.println("Вы успешно отписались от подкаста");
        } catch (Exception e) {
            System.out.println("Ошибка отписки: " + e.getMessage());
        }
    }

    private void subscribeToPodcast() throws IOException {
        System.out.println("Введите UUID пользователя:");
        String userId = bufferedReader.readLine();

        System.out.println("Введите UUID подкаста:");
        String podcastId = bufferedReader.readLine();

        try {
            userService.subscribeToPodcast(
                    UUID.fromString(userId),
                    UUID.fromString(podcastId)
            );
            System.out.println("Подписка успешно оформлена");

            System.out.println("Текущие подписки пользователя:");
            List<PodcastDTO> subscriptions = userService.getUserSubscriptions(UUID.fromString(userId));
            subscriptions.forEach(podcast ->
                    System.out.printf("- %s (Создатель: %s)%n",
                            podcast.getTitle(),
                            podcast.getCreatorName())
            );

        } catch (IllegalStateException | EntityNotFoundException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла неожиданная ошибка: " + e.getMessage());
        }
    }

    private void viewSubscriptions() throws IOException {
        System.out.println("Введите UUID пользователя:");
        String userId = bufferedReader.readLine();

        try {
            List<PodcastDTO> subscriptions = userService.getUserSubscriptions(UUID.fromString(userId));
            if (subscriptions.isEmpty()) {
                System.out.println("У пользователя нет подписок");
                return;
            }

            System.out.println("Подписки пользователя:");
            subscriptions.forEach(podcast ->
                    System.out.printf("- %s (Создатель: %s, Категория: %s)%n",
                            podcast.getTitle(),
                            podcast.getCreatorName(),
                            podcast.getCategoryName())
            );
        } catch (Exception e) {
            System.out.println("Ошибка просмотра подписок: " + e.getMessage());
        }
    }
}
