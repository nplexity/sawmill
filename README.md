# Sawmill

Enhanced logging for Android.

The design of Sawmill was heavily influenced by Robbie Hanson's [CocoaLumberjack](https://github.com/CocoaLumberjack/CocoaLumberjack), so anyone familiar with it should find this easy to understand.

## Getting Started

Download the latest jar from the [releases](https://github.com/nplexity/sawmill/releases) page and include it in your project like you would any other library jar.

Logs are sent to `Logger` instances, which must be added to `Sawmill` in order to receive logs.  As early in the app's lifecycle as possible (presumably in the `onCreate()` of your `Application` class), you'll want to do something like the following:

```
Sawmill.addLogger(LogcatLogger.getInstance());
```

This adds the provided `LogcatLogger` instance to `Sawmill` to capture logs at all levels.  Note that should only add each `Logger` once to `Sawmill`, or you'll see duplicated logs being sent to it.

Anywhere you want to log a message, use one of the static methods below.

### Methods

*Make sure you have the correct version of `Log.java` imported in your class.*

- `Log.error(T... args)`
- `Log.warning(T... args)`
- `Log.info(T... args)`
- `Log.debug(T... args)`
- `Log.verbose(T... args)`
- `Log.trace()`

Each of these methods use varargs rather than a string so that the invocation of `toString()` is delayed as long as possible.  Depending on the verbosity level of your loggers, `Sawmill` will avoid creating a `LogMessage` unless there's actually a `Logger` instance expecting to receive it.

This means that for performance reasons it's preferrable to invoke each of these methods in the following manner:

```
// Performance optimization using ','
Log.warning("Something went wrong with myObj: ", myObj);

// Slightly slower, since `myObj.toString()` must be invoked during the method call
Log.warning("Something went wrong with myObj: " + myObj);
```

All of the methods except for `Log.trace()` accept a message to print out.  Tracing is useful for situations when you don't actually need any information other than tracing method invocations through your code.  `Log.trace()` will generate a `VERBOSE` priority log message containing the method name and line number where it's placed.

### Custom Loggers and LogFormatters

Each method collects the same class name, method name, and line number that `Log.trace()` does and includes it in the `LogMessage` sent to each instance of `Logger`.  Additionally, a `tag` is generated (using the class name), a timestamp is collected, and obviously the message itself that should be logged is added to `LogMessage`.  This allows you to modify the format of your logs and to include or exclude certain information.

`LogcatLogger` is provided since it is so common to use, but you're free to create custom `Logger` instances for whatever need you may have.  Simply implement the `Logger` interface and add your instance to `Sawmill`.

Each `Logger` can have a specific `LogFormatter`, which describes how to convert the provided `LogMessage` into a formatted `String` that will be logged.  You'll need to provide a fallback message format in the case that a `LogFormatter` isn't added to your `Logger`.  Just like `Logger`, all you need to do for `LogFormatter` is implement its interface.  Once you have an instance of each, call `myLogger.setLogFormatter(myFormatter)`, and all messages from that point on will be formatted as you have specified.

### LogLevels

Every `LogMessage` has a `LogFlag` indicating its verbosity.  Whenever you add a `Logger` to `Sawmill`, you have the option to provide an `EnumSet<LogFlag>` explaining which `LogFlag`s you want this `Logger` to receive messages for.  The default, as shown above, is `EnumSet.allOf(LogFlag.class)`, or `LogLevel.ALL`, which will convert to an `EnumSet<LogFlag>` as needed.

## Developed By

[Jonathon Staff](http://jonathonstaff.com)

# License

    Copyright 2015 nplexity

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
