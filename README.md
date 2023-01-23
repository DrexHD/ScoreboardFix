# Scoreboard Fix
Scoreboard Fix is a very simple mod that attempts to fix the issue that player scoreboard entries don't use UUIDs and hence are not updated when a player changes their name!

## How it works
If a player joins and changed their name, this mod will copy all scoreboard entries with their old name and copy them to their new name (old entries will be deleted)!

## Limitations
- The mod uses the user cache (caches player names for 30 days) to determine if a player changed their name. If a player is no longer cached, the name change will not be detected!
- The mod will also copy fakeplayer data, if the name matches (it is recommended to prefix fake player entries with `#`)
