# 🧩 BetaPatches
**A plugin for Minecraft Beta 1.7.3 (Bukkit 1060)** that patches known exploits, dupe glitches, lag machines, and illegal item usage.

---

## ✨ Features

| Patch Name                | Description |
|---------------------------|-------------|
| `FreecamDupePatch`        | Blocks instamine + chest access duplication  
| `ChunkCrashPatch`         | Prevents boat spam chunk crashes  
| `PistonDupePatch`         | Patches piston + chest duplication glitch  
| `NoBedrock`               | Blocks placement of bedrock  
| `NoIllegals`              | Removes illegal blocks/items from inventories  
| `NegativeItemScan`        | Scans containers for negative items and removes them  
| `LoginMovementBlocker`    | Prevents movement before login (xAuth compatible)  
| `KillCommand`             | Adds `/kill` command to suicide instantly  
| `AntiFastBreak`           | Prevents mining too fast (nuker/speedmine clients)

---

## ⚙️ Compatibility

- **Minecraft Version:** Beta 1.7.3  
- **Bukkit Build:** 1060  
- **Java Version:** 6 or 7

---

## 🛠 File Structure

src/
└─ net/utils/betapatches/
├─ BetaPatches.java
├─ FreecamDupePatchBlock.java
├─ FreecamDupePatchPlayer.java
├─ ChunkCrashPatch.java
├─ PistonDupePatch.java
├─ NoBedrock.java
├─ NoIllegals.java
├─ NegativeItemScan.java
├─ LoginMovementBlocker.java
├─ KillCommand.java
├─ AntiFastBreak.java

---

## 👤 Author

Created by Xera for retro/anarchy-style servers like `2b2t legacy`.
