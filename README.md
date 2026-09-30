# Little Lemon Food Ordering App

A modern Android food ordering application built with **Jetpack Compose**, **Room Database**, and **Ktor** networking. This app allows users to browse a Mediterranean restaurant's menu, filter by category, search for items, and manage their profile.

## Features

✨ **User Authentication**
- User registration with email validation
- Persistent user data storage in SharedPreferences
- Auto-login on app restart
- Logout functionality

📱 **Home Screen**
- Hero banner with restaurant information
- Search functionality (by title and description)
- Category filtering (All, Starters, Mains, Desserts)
- Menu items display with images, descriptions, and prices
- Real-time menu fetching from remote API

👤 **Profile Screen**
- View saved user information (Name, Email)
- Logout with data cleanup
- Back navigation to Home

🔄 **Navigation**
- Bottom navigation between screens
- State-based routing (Onboarding → Home → Profile)
- Proper back stack management

## Tech Stack

### Architecture & UI
- **Jetpack Compose** - Modern declarative UI framework
- **Material 3** - Latest Material Design components
- **MVVM Pattern** - ViewModel-based state management
- **LiveData** - Reactive data binding

### Data Management
- **Room Database** - Local SQLite persistence
- **SharedPreferences** - User credential storage
- **Ktor Client** - HTTP networking with JSON serialization
- **Kotlinx Serialization** - JSON deserialization

### Networking
- **Ktor HTTP Client** - Async HTTP requests
- **Content Negotiation** - Automatic JSON conversion
- **GitHub Raw Content API** - Menu data source

### Build & Dependencies
- **Gradle** - Build system
- **Kotlin** - Primary language
- **Android API 24+** - Minimum SDK support

## Project Structure

```
MiniDataProject/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/littlelemon/
│   │   │   ├── MainActivity.kt              # Entry point, HTTP & DB setup
│   │   │   ├── Database.kt                  # Room entities, DAO, database config
│   │   │   ├── Network.kt                   # Ktor data models for JSON
│   │   │   ├── Destinations.kt              # Navigation route definitions
│   │   │   ├── NavigationComposable.kt      # NavHost & navigation logic
│   │   │   └── composables/
│   │   │       ├── Onboarding.kt            # Registration screen
│   │   │       ├── Home.kt                  # Menu display & filtering
│   │   │       └── Profile.kt               # User profile & logout
│   │   └── res/                             # Resources (drawables, strings, etc.)
│   └── build.gradle                         # Dependencies & build config
└── README.md                                # This file
```

## Installation & Setup

### Prerequisites
- Android Studio (2022.1 or later)
- Android SDK 24+ (API level 24 minimum, compileSdk 34)
- JDK 11 or higher
- Internet connection (for menu API)

### Steps

1. **Clone/Extract the project**
   ```bash
   cd MiniDataProject
   ```

2. **Open in Android Studio**
   - File → Open → Select project root
   - Android Studio will auto-sync Gradle

3. **Configure Local Properties** (if needed)
   ```bash
   # Create local.properties in project root
   sdk.dir=/path/to/android/sdk
   ```

4. **Build the project**
   ```bash
   ./gradlew build
   ```

5. **Run on emulator or device**
   - Connect device via USB or launch emulator
   - Click "Run" in Android Studio
   - Or: `./gradlew installDebug`

## API Integration

### Menu Data Source
- **Endpoint**: `https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json`
- **Format**: JSON array with menu items
- **Caching**: First fetch stored in Room database, subsequent loads from local cache
- **Fields**: id, title, description, price, image (URL), category

### Sample Response
```json
{
  "menu": [
    {
      "id": 1,
      "title": "Greek Salad",
      "description": "The famous greek salad of crispy lettuce, peppers, olives...",
      "price": "10",
      "image": "https://github.com/.../greekSalad.jpg?raw=true",
      "category": "starters"
    }
  ]
}
```

## Key Implementation Details

### Database Migration
- Room schema version: **2**
- Fallback: `fallbackToDestructiveMigration()` enabled for development
- Tables: `MenuItemRoom` (id, title, description, price, image, category)

### HTTP Client Configuration
```kotlin
// Handles multiple content types (JSON, plain text, HTML)
// Lenient parsing for API flexibility
// Ignores unknown JSON fields
```

### Navigation Flow
```
App Launch
    ↓
Check SharedPreferences
    ↓
┌─→ User logged in? → Home Screen
│                        ↓
│                   Profile (via icon)
│                        ↓
│                   Logout → Onboarding
│
└─→ Not logged in? → Onboarding Screen
                         ↓
                    Register → Home Screen
```

## Features Implementation

### Search Filtering
- Real-time search as user types
- Searches both title and description
- Case-insensitive matching
- Combines with category filter

### Category Filtering
- Buttons: All, Starters, Mains, Desserts
- Exact category matching
- Visual feedback (selected button styling)
- Case-insensitive category comparison

### Image Display
- GlideImage for efficient image loading
- Remote URL loading from GitHub
- Proper scaling and cropping
- Placeholder handling for empty images

### Data Persistence
- User data in SharedPreferences (firstName, lastName, email)
- Menu items in Room database with LiveData
- Auto-refresh on app restart
- Automatic cache invalidation on schema changes

## Building & Testing

### Debug Build
```bash
./gradlew assembleDebug
```

### Run Unit Tests
```bash
./gradlew test
```

### Run Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

### Generate Release Build
```bash
./gradlew assembleRelease
```

## Troubleshooting

### Issue: "Kotlin reflection is not available"
**Solution**: Ensure `@OptIn(ExperimentalSerializationApi::class)` is applied to HTTP client configuration.

### Issue: "No transformation found" error
**Solution**: Content negotiation is configured for text/plain, application/json, and text/html.

### Issue: Menu items not displaying
**Check**:
1. Network connection is active
2. GitHub API is accessible
3. Room database migration completed successfully
4. Check Logcat for error messages

### Issue: Images not loading
**Check**:
1. Image URLs in API response are valid
2. Glide is properly imported and configured
3. Network permission is granted in manifest

## Permissions

Required permissions in `AndroidManifest.xml`:
- `android.permission.INTERNET` - For API calls and image loading

## Dependencies

Key dependencies (see `build.gradle` for full list):
- **Compose UI**: `androidx.compose.ui:ui:*`
- **Material 3**: `androidx.compose.material3:material3:1.2.1`
- **Navigation**: `androidx.navigation:navigation-compose:2.5.3`
- **Room**: `androidx.room:room-runtime:2.4.3`
- **Ktor**: `io.ktor:ktor-client-android:2.1.3`
- **Glide**: `com.github.bumptech.glide:compose:1.0.0-alpha.1`

## Future Enhancements

🔮 **Planned Features**
- Add to cart functionality
- Order history
- User preferences (favorite items, dietary restrictions)
- Push notifications for order updates
- Payment integration
- Reviews and ratings
- Delivery tracking
- Multiple delivery addresses
- Loyalty program

## Architecture Notes

### Separation of Concerns
- **MainActivity**: HTTP client setup, database initialization
- **Composables**: Pure UI logic with Compose
- **Database**: Room entities, DAOs, schema
- **Navigation**: Centralized routing logic
- **Network**: API models and serialization

### State Management
- **Compose State**: Local UI state (search, category filter)
- **LiveData**: Database observations in Home screen
- **SharedPreferences**: Persistent user authentication

### Error Handling
- Database migration fallback
- Lenient JSON parsing for API flexibility
- Null safety with Kotlin optionals
- Graceful image loading fallbacks

## License

This project is part of the Little Lemon Restaurant application.

## Support

For issues, questions, or contributions, please refer to the project documentation or contact the development team.

---

**Version**: 1.0.0  
**Last Updated**: August 2026  
**Status**: Production Ready ✅

