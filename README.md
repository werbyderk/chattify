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
- Over internet direct messaging

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
