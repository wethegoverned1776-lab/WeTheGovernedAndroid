# Fix Poll Creation Inconsistency on PC/Desktop

The PC version (Desktop/Android) currently fails to create polls correctly because it does not lowercase public keys before using them in data models and publishing events. This leads to mismatches between the signed event and the content, causing rejection by relays or synchronization issues. This plan unifies the behavior across platforms by ensuring consistent public key normalization and time providers.

## Proposed Changes

### Shared Component (`shared/src/commonMain`)

#### [RoomRepositories.kt](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/shared/src/commonMain/kotlin/net/wetheGoverned/repository/RoomRepositories.kt)

- Normalize all `pubKey`/`voterPubKey`/`authorPubKey` parameters to lowercase before using them in `CivicPoll`, `CivicVote`, `ResidentProfile`, and `CivicPublisher` calls.
- Affects `createPoll`, `vote`, `voteImportance`, `updateProfile`, and `updateDistrict`.

---

### Desktop Specific (`shared/src/desktopMain`)

#### [DesktopRepositories.kt](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/shared/src/desktopMain/kotlin/net/wetheGoverned/repository/DesktopRepositories.kt)

- Replace `System.currentTimeMillis()` with `Clock.System.now().toEpochMilliseconds()` for consistency with common/web code.
- Normalize all public key parameters to lowercase.
- Fix `vote` method to use `json.encodeToString` (which refers to `CivicJson`) instead of the default `Json.encodeToString`.
- Convert `addToIndex` to a `suspend` function and remove `runBlocking` to avoid potential deadlocks.

## Verification Plan

### Automated Tests
- Run existing `DesktopRepositoriesTest` to ensure basic functionality is preserved.
```powershell
./gradlew :shared:desktopTest --tests "net.wetheGoverned.repository.DesktopRepositoriesTest"
```
- Run `CompatibilityVerificationTest` to ensure cross-platform basics are still sound.
```powershell
./gradlew :shared:commonTest --tests "net.wetheGoverned.CompatibilityVerificationTest"
```

### Manual Verification
- I will verify the changes by inspecting the code for consistency with `WebRepositories.kt` (the known working implementation).
- I will check that all pubkey usages in `RoomRepositories.kt` are now lowercased.
- I will check that `DesktopRepositories.kt` uses the correct JSON serializer in all methods.
