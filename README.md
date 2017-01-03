# AudioController

## Usage

### Example
#### Get the controller
```java
AudioController master = Audio.getMasterController();
```
```java
AudioController headset = Audio.getHeadsetController();
```

#### Get the volume
```java
float volume = master.getVolume();
```

#### Set the volume
```java
master.setVolume(0.5f);
```

#### Is the output muted
```java
boolean muted = master.isMuted();
```

#### Mute/Unmute the output
```java
master.setMuted(true);
// Or
master.setMuted(false);
```

## License
This project is licensed under the [MIT License](http://en.wikipedia.org/wiki/MIT_License).

## ToDo
 * Add a little summary
 * JavaDoc
 * Transform to Maven project