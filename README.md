# 🧩 BetaPatches
**A plugin for Minecraft Beta 1.7.3 (Bukkit 1060)** that patches old exploits and dupes, preserving server stability on anarchy or retro servers.

---

## ✨ Features

- ✅ **Freecam + Instamine Dupe Patch**  
  Prevents players from interacting with chests from far away or instantly breaking them to duplicate items.

- 🚫 **Chunk Crash Patch** (Boat Spam)  
  Cancels boat placement to prevent chunk overloads that crash clients or corrupt regions.

- 🔧 **Piston Dupe Patch**  
  Blocks the placement of pistons entirely to stop known duplication exploits involving block updates.

---

## ⚙️ Compatibility

- **Minecraft Version:** Beta 1.7.3  
- **Bukkit Build:** 1060  
- **Java Version:** 6 or 7

---

## 🛠 File Structure

- `BetaPatches.java` – Main plugin class  
- `FreecamDupePatch.java` – Prevents chest dupe via freecam/instamine  
- `ChunkCrashPatch.java` – Blocks boats to prevent chunk crashes  
- `PistonDupePatch.java` – Prevents piston placement  
- `plugin.yml` – Bukkit plugin definition file

---

## 👤 Author

Created by Xera for retro/anarchy-style servers like `2b2t legacy`.
