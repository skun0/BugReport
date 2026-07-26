# 🐛 Bug Report
![Version](https://img.shields.io/badge/version-1.0.0-blue)
![Minecraft](https://img.shields.io/badge/Minecraft-1.21.11-green)

A simple and lightweight Minecraft plugin that allows players to report bugs directly from the server.

## ✨ Features

- 🐛 Submit bug reports with a simple command
- 🆔 Automatic bug ID generation
- 💾 Saves reports in `bugs.yml`
- 🔔 Staff notifications
- 🔊 Sound notification for staff members
- ⏳ 5 minute cooldown
- 🔑 Permission-based cooldown bypass
- ⚙️ Customizable messages
- 📌 Bug status management

---

## 📦 Installation

1. Download `BugReport.jar`
2. Put the jar file into your server's:

```
plugins/
```

folder.

3. Restart your server.
4. Configure `messages.yml`.

---

## 🎮 Commands

### Players

### `/bug <message>`

Creates a new bug report.

Example:

```
/bug The scoreboard is not updating
```

The player will receive a confirmation message with the bug ID.

---

## 🔑 Permissions

| Permission | Description |
|---|---|
| `bug.notify` | Receive staff bug notifications |
| `bug.cooldownbypass` | Bypass the report cooldown |

---

## ⏳ Cooldown

Players have a **5 minute cooldown** between bug reports.

Players with:

```
bug.cooldownbypass
```

can ignore the cooldown.

---

## 📁 Files

The plugin creates:

```
plugins/BugReport/
│
├── bugs.yml
└── messages.yml
```

---

## 📝 bugs.yml

Bug reports are stored with:

- ID
- Player name
- Message
- Status
- Date

Example:

```yml
last-id: 1

bugs:
  1:
    player: Steve
    message: "The scoreboard is broken"
    status: OPEN
    date: "26/07/2026 16:30:00"
```

---

## ⚙️ messages.yml

Messages can be customized using colors and placeholders.

Available placeholders:

| Placeholder | Description |
|---|---|
| `{prefix}` | Plugin prefix |
| `{id}` | Bug ID |
| `{player}` | Player name |
| `{message}` | Bug message |
| `{time}` | Remaining cooldown |

---

## 🛠 Requirements

- Minecraft: 1.21.x
- Java: 21+

---

## 📄 License

MIT License
