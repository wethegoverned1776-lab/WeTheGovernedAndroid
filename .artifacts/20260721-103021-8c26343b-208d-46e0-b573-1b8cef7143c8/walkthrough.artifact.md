# Rebuild Wasm/JS Artifacts Walkthrough

I have updated the WeTheGoverned codebase to resolve persistence issues on the Web and ensure protocol compliance for cross-platform synchronization.

## Key Accomplishments

### 1. Nostr Protocol Compliance (NIP-01)
- **Pubkey Normalization**: Fixed a critical issue where mixed-case or placeholder pubkeys (like "Admin") caused relays to silently reject events.
- **Normalization Logic**: Enforced strictly lowercase hex strings for all pubkeys and event tags in [WsCivicPublisher.kt](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/shared/src/commonMain/kotlin/net/wetheGoverned/data/WsCivicPublisher.kt) and [P2PSyncEngine.kt](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/shared/src/commonMain/kotlin/net/wetheGoverned/data/P2PSyncEngine.kt).

### 2. Admin User & Verification
- **Verified Tier**: Modified [RoomRepositories.kt](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/shared/src/commonMain/kotlin/net/wetheGoverned/repository/RoomRepositories.kt) to automatically assign the `VERIFIED` tier to the 'admin' user upon login.
- **Poll Creation**: This enables the poll creation (+) button for the admin, which previously was hidden due to `OBSERVER` status.

### 3. Wasm Persistence Stability
- **LocalStorage Fallback**: Implemented [WebRepositories.kt](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/shared/src/wasmJsMain/kotlin/net/wetheGoverned/repository/WebRepositories.kt) to use browser `localStorage` as a fallback for the Wasm target.
- **Room Bypass**: Switched the Wasm entry point in [Main.kt](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/shared/src/wasmJsMain/kotlin/net/wetheGoverned/Main.kt) to use these web-specific repositories, bypassing a known internal compiler error in Room 3.0's Wasm distribution logic.

### 4. UI & Error Handling
- **Global Notifications**: Added a snackbar host to [HomeScreen.kt](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/shared/src/commonMain/kotlin/net/wetheGoverned/ui/home/HomeScreen.kt) to display real-time errors (e.g., relay rejections or sync failures) broadcast via the `GlobalNotification` system.

## Verification Results

### Logic Validation
- **Pubkey Normalization**: Verified via logs that pubkeys are converted to lowercase before event signing.
- **Admin Verification**: Confirmed that the admin user is created with `VerificationTier.VERIFIED` in the local DB.

### Build Status
- **Android/Desktop**: Builds successfully and includes all logic fixes.
- **Wasm Distribution**: Encountered a persistent Kotlin 2.0+ `NullPointerException` in the `kotlinx-serialization` compiler plugin during FIR resolution.
- **Mitigation**: Pushed all source fixes to the `web-wasm` branch. Updated [index.html](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/docs/index.html) and added a stub [worker.js](file:///C:/Users/rodne/Desktop/WE_THE_Governed/WETHEGOVERNED/docs/worker.js) to the `docs/` folder to prepare for a successful build from a stable CI environment.

## Next Steps
- **CI Build**: Trigger a remote build to generate the final `shared.js` and `shared.wasm` artifacts for GitHub Pages.
- **Room 3.0 Wasm**: Monitor Kotlin compiler updates to restore full Room persistence for the Web target once the FIR serialization bug is resolved upstream.
