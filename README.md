# Приложение TrackVendor
Приложение TrackVendor, разработанно для мониторинга подключения и отключения пользователя к сети wi-fi в фоновом режиме, для сохранения данных о сети использовалась бд Room
![Screenshot_1657720138](https://user-images.githubusercontent.com/79585100/189517881-b1c09f9b-d007-4c96-aed8-21c90ffece8c.png)
![Screenshot_1662883802](https://user-images.githubusercontent.com/79585100/189518049-f0ecfadf-0f55-4fa2-96bc-c41482ac7215.png)
Стэк используемые в проекте:Dagger 2, Kotlin Coroutines, Jetpack Compose, Room, WorkManager.
Изначально был настроен ворк менеджер для мониторинга сети, запускались переодические задачи, в дальнейшем проект переписался на сервис который отлично справился с этой задачей.
