# Implementation Plan - Room KMP and Mesh Discovery

## Goal Description
Achieve robust cross-platform persistence and local connectivity by implementing a unified Room 3.0 (KMP) database and extending mesh discovery to all targets (JVM and Wasm).

## User Review Required
- **Room 3.0 Package Change**: Migrating from `androidx.room` to `androidx.room3` is required for Wasm support. This involves updating all imports and DAOs (DAOs must use `suspend` for all methods).
- **Wasm Discovery Fallback**: Wasm (browser) will use a Nostr-based discovery mechanism (NIP-67/custom) as a fallback for mDNS/UDP, allowing web clients to join the local mesh via a shared relay context.

## Proposed Changes

### [shared] module - Room KMP Persistence

#### [build.gradle.kts](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/shared/build.gradle.kts)
- Add Room 3.0 plugin: `id("androidx.room3") version "3.0.0-alpha01"`.
- Add dependencies: `androidx.room3:room3-runtime`, `androidx.sqlite:sqlite-bundled`.
- Configure KSP for all targets (`kspCommonMainMetadata`, `kspAndroid`, `kspDesktop`, `kspWasmJs`).

#### [NEW] [CivicEntities.kt](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/shared/src/commonMain/kotlin/net/wetheGoverned/data/local/entity/CivicEntities.kt)
- Unified Room entities migrated from `app` module.
- Ensure all types are KMP-compatible or handled by `CivicConverters`.

#### [NEW] [CivicDaos.kt](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/shared/src/commonMain/kotlin/net/wetheGoverned/data/local/dao/CivicDaos.kt)
- Unified Room DAOs migrated from `app` module.
- **Critical**: All methods converted to `suspend` for Wasm compatibility.

#### [NEW] [AppDatabase.kt](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/shared/src/commonMain/kotlin/net/wetheGoverned/data/local/AppDatabase.kt)
- Main database class using `@ConstructedBy(AppDatabaseConstructor::class)`.
- `expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase>`.

#### [NEW] [DatabaseBuilder.kt](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/shared/src/commonMain/kotlin/net/wetheGoverned/data/local/DatabaseBuilder.kt)
- `expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>`.

#### Platform Implementations of `DatabaseBuilder`
- [androidMain](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/shared/src/androidMain/kotlin/net/wetheGoverned/data/local/DatabaseBuilder.android.kt): Uses `Context.getDatabasePath`.
- [desktopMain](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/shared/src/desktopMain/kotlin/net/wetheGoverned/data/local/DatabaseBuilder.desktop.kt): Uses `System.getProperty("user.home")`.
- [wasmJsMain](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/shared/src/wasmJsMain/kotlin/net/wetheGoverned/data/local/DatabaseBuilder.wasm.kt): Uses `WebWorkerSQLiteDriver`.

### [shared] module - Repositories

#### [NEW] [RoomRepositories.kt](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/shared/src/commonMain/kotlin/net/wetheGoverned/repository/RoomRepositories.kt)
- Implement `PollRepository`, `ResidentRepository`, etc., using Room DAOs.
- This will unify the logic across Android, Desktop, and Wasm.

### [shared] module - Mesh Discovery

#### [NEW] [WasmMeshDiscoveryManager.kt](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/shared/src/wasmJsMain/kotlin/net/wetheGoverned/data/WasmMeshDiscoveryManager.kt)
- Wasm implementation of `MeshDiscoveryManager` using a Nostr-based discovery fallback.

---

### [app] module

#### [build.gradle.kts](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/app/build.gradle.kts)
- Upgrade Room to 3.0 and update imports.
- Remove local DAOs/Entities once migrated to `shared`.

## Verification Plan

### Automated Tests
- `./gradlew :shared:allTests`: Verify core sync and signing logic.
- New `RoomPersistenceTest.kt` in `commonTest`: Verify CRUD operations for all entities on JVM and Wasm (using in-memory SQLite).

### Manual Verification
- **Cross-Platform Persistence**: Create a poll on Desktop, vote on Android, refresh Web, verify data persists and syncs across all.
- **Wasm Storage**: Inspect browser DevTools to verify SQLite file in Origin Private File System (OPFS).
- **Mesh Discovery**: Verify Desktop node discovers Android node IP via Logcat/Console output.
