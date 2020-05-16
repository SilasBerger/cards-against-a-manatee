# Cards on the Web Prototype - Backend
Repository for the backend of the _Cards on the Web_ prototype. The code in this repository should eventually serve
as a working prototype.

## Concepts
- **Remote Player:** Representation of a physical remotePlayer living outside the backend.
- **Lobby:** A virtual room in players can gather and play.
- **Lobby Member:** A remotePlayer in a lobby, whether human or NPC, local or remote.
- **Player:** Synonym for either a remote remotePlayer or lobby member, depending on the context.
- **Player Comm Channel:** Abstract class that relays commands to and messages from a remote remotePlayer over an arbitrary
  communication medium (WebSocket, shell interface, etc.).
- **Command:** TBA
- **Message:** TBA
- **Game:** TBA
- **Move:** TBA

## Refactoring
- Adding a new event type requires a lot of boilerplate code. Implementing the event and adding a default method to the
  EventHandler interface is okay. However, it would be useful if there was a way to, for instance, tell the CommChannel
  that it should send every event to the client, if it's either a notification or client error event. Right now, we have
  to add a handle() overload to the comm channel for each such event, and I think that shouldn't be necessary.
- It's not always quite clear who has to do what, when a new event happens. For instance, the theory way that, when a
  player leaves the lobby, the RemotePlayer takes care of the player side of things (setting the current lobby to null,
  unsubscribing from the lobby channel, etc.), the lobby takes care of removing the player from its member list, etc.
  However, this has already proven to result in some race conditions, where, depending on which handler gets called
  first, the exiting player may or may not receive an event that the lobby uses to tell the other members about the
  player having left. We resolved this by not letting the lobby handle player exit events (even though it would receive
  them), and having the remoteplayer call the lobby to tell it that the player has left.

## Player Commands vs. Messages
TODO: Everything beyond this point needs to be reworked, not that we switched to a more generic event architecture.

Once a remotePlayer is connected to the outside world through a `PlayerCommChannel`, this channel communicates with its
agent through the use of remotePlayer commands and messages. A remotePlayer command is a transmission coming in from the outside.
It represents a command that the local remotePlayer should execute on behalf of the remotePlayer's agent (e.g. the web frontend).
Messages serve as the opposite communication direction. A message is sent from the backend to the players' clients.
Messages can be directed to a specific remotePlayer, to a group of players (e.g. participants in the current game), or to all
players in the same lobby (lobby members). Theoretically, it would also be possible to send a message to all players
across all lobbies, such as announcing a server shutdown. However, there currently is no facility for that, other than
to fetch all lobbies from the lobby registry and asking them for their players.

## Player Command Protocol: General Format
All remotePlayer commands are JSON objects that must respect the following format:

```
{
  "id": string,
  "iat": long,
  "data": object
}
```

The fields in this structure have the following semantics:
- `id`: A dot-separated lowercase string identifying this remotePlayer command's type. Command types include lobby operations
  (join, exit), game operations (start, end), game-mode specific operations (make a move), etc. A command's type is used
  to determine the appropriate handler in the backend. Additionally, the expected structure of the `data` field is
  defined solely by what data is expected for this particular command type.
- `iat`: _issued at_ timestamp, denoting the UTC epoch time at which the remotePlayer agent created this command. This field
  can in some cases be used to handle pending remotePlayer command on the same order in which they were created, although this
  behavior is generally not guaranteed or even attempted.
- `data`: Arbitrary JSON object containing the data for this command. The format of this object is defined by the
  requirements of the specific command type, denoted by the `id` field. An empty `data` object has to be provided at
  all times, even for commands that don't require specific data.

## Player Commands
TBA. Responses are generally understood as messages sent only to the sender of a remotePlayer command, unless stated
otherwise.

### Group `general`
#### `general.dummy`
**Description:** No meaning, just for testing.<br><br>
**Expected Data:**
```
data: {
  "text": string
}
```
**Responses:**<br>
_No responses_

#### `general.request_join_lobby`
**Description:** Request joining lobby with specified lobby id.<br><br>
**Expected Data:**
```
data: {
  "lobbyId": NonceId
}
```
**Responses:**<br>
- `lobby.notify_current_state`: if successful, sent to the requesting remotePlayer.
- `lobby.notify_player_joined`: if successful, sent to all other lobby members, not including the requesting remotePlayer.
- `error.general.no_such_lobby`: if no lobby exists with the requested id.

### Group `lobby`
#### `lobby.request_exit_lobby`
**Description:** Request leaving current lobby.<br><br>
**Expected Data:**
```
data: {}
```
**Responses:**<br>
- `error.general.not_in_a_lobby`: if the remotePlayer is not currently in a lobby.

## Message Protocol
TBA.

## Messages
### Group `lobby`
#### `lobby.notify_current_state`
**Description:** TBA<br><br>
**Provided Data:**
```
data: LobbyState
```

#### `lobby.notify_player_joined`
**Description:** A new remotePlayer has joined this remotePlayer's current lobby (but won't join a currently active game).<br><br>
**Provided Data:**
```
data: Player
```

### Group `error.general`
#### `error.general.general_error`
**Description:** Non-specific error message.<br><br>
**Provided Data:**
```
data: {
  "error": string
}
```

#### `error.general.invalid_command_error`
**Description:** Specified command is invalid and cannot be processed.<br><br>
**Provided Data:**
```
data: {
  "command": string
}
```

## Group `error.comm`
Low-level errors on communication medium level (e.g. WebSocket).

#### `error.comm.unexpected_communication_error`
**Description:** An error has occurred while sending or receiving data over this communication medium.<br><br>
**Provided Data:**
```
data: {
  "error": string
}
```


## Custom Data Types
### NonceId
`c{4}-c{4}-c{4}` for `c` in `ABCDEFGHJKLMNPQRSTUVWXYZ1234567890`.

## Model
### Player
```
{
  "id": UUID,
  "name": string,  
  "score": int
}
```

### LobbyState
```
{
  "id": NonceId,
  "members": Player[],
  "leaderId": UUID,
  "activeGameMode": string | null
}
```
