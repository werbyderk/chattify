#Chattify
###A lightweight, peer to peer chat program
__Chattify is the most versatile chat program made yet. Instead of depending on a dedicated server, Chattify uses a BitTorrent-inspired system to allow users to communicate in a P2P environment.__

###How it works
Instead of having a traditional server-client system to send messages, Chattify implements server and client properties into a single application. When the 'client' portion of one computer running Chattify connects to the 'server' of another Chattify instance and vice-versa, the two machines are able to exchange messages.

###Known issues
- User will receive ``null`` from disconnecting users
- User list won’t update when a user disconnects
- Messages won’t be received or sent properly with more than 2 users connected
- Messages can be sent when the client part of the program is disconnected

###Roadmap
- Image and file support
- Allow resizing of messages pane and users pane
- Notifications
- Better handling of changing username
- Direct messaging
- Ability to block a user
- Over internet direct messaging (?)
- Score system (?)

###Changelog
#####Version 0.1
- Initial release

#####Version 0.1.1
- Support for saving username
- Replaced username textfield with settings button
- Bug fixes

#####Version 0.1.2
- Added username validation
- Bug fixes

#####Version 0.1.2.1
- Fixed running jar file without JVM arguments

#####Version 0.2
- Made user pane and messages pane resizable
- Added horizontal scrolling for messages pane
- Made `Server.class`'s TCP port dynamic: `Client.class` will retrieve the UDP port from instance of `Server.class`, and the server will close that port and use it for TCP
- Redid config file’s I/O
- Redid `Settings.class`'s menu
- Added control for client’s UDP timeout to allow quicker connections at the sacrifice of efficiency
- Minor layout adjustments
- Added some verbosity to `System.out.println()` logging for debugging
- Bug fixes
- Got rid of Git trash text on source files
