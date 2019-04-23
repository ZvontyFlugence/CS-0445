package cs445.a4;

/**
 * This abstract data type represents the backend for a streaming radio service.
 * It stores the songs, stations, and users in the system, as well as the
 * ratings that users assign to songs.
 */
public interface StreamingRadio {

    /**
     * The abstract methods below are declared as void methods with no
     * parameters. You need to expand each declaration to specify a return type
     * and parameters, as necessary. You also need to include a detailed comment
     * for each abstract method describing its effect, its return value, any
     * corner cases that the client may need to consider, any exceptions the
     * method may throw (including a description of the circumstances under
     * which this will happen), and so on. You should include enough details
     * that a client could use this data structure without ever being surprised
     * or not knowing what will happen, even though they haven't read the
     * implementation.
     */

    /**
     * Adds a new song to the system. If the given song has already been added
     * to the system, then the method will not add anything to the system and
     * return false. Should the given song be null, the
     * method will not add anything to the system and will throw a
     * NullPointerException.
     *
     * @param song song which should be added to the system
     * @return true if song is added successfully, false if song fails to be added
     * @throws NullPointerException if song is null
     */
    boolean addSong(Song song) throws NullPointerException;

    /**
     * Removes a song from the system. If the song does not already exist in the
     * system, the system will not be changed and the method will return false.
     * Should the song be null, the method will throw a NullPointerException.
     *
     * @param song song which should be removed from the system
     * @retun true if song is successfully removed, false if song removal fails
     * @throws NullPointerException if the song is null
     */
    boolean removeSong(Song song) throws NullPointerException;

    /**
     * Adds an existing song to the playlist for an existing radio station.
     * If either the song or station is null, nothing is changed, and the method
     * will throw a NullPointerException. Should the song already exist in the
     * station's playlist, then the method will return false. If the song, or
     * the station, does not exist within the system, the method will throw
     * an IllegalArgumentException.
     *
     * @param song song which should be added to the playlist for a given station
     * @param station station which should have a given song added to its playlist
     * @return true if song is added to the station, false otherwise
     * @throws IllegalArgumentException if song or station doesn't exist within the system
     * @throws NullPointerException if either the song or station is null
     */
    boolean addToStation(Song song, Station station) throws IllegalArgumentException, NullPointerException;

    /**
     * Removes a song from the playlist for a radio station. Should either the song
     * or station be null, then the method will throw a NullPointerException.
     * Should the song not already exist in the station's playlist, then the playlist
     * will not be altered, and the method will return false. If either the song or
     * station does not exist within the system, then the method will throw an
     * IllegalArgumentException.
     *
     * @param song song which should be removed from the playlist for a given station
     * @param station station which should have a given song removed from its playlist
     * @return true if song is removed, false otherwise
     * @throws IllegalArgumentException if either the song or station doesn't exist
     * within the system
     * @throws NullPointerException if either the song or station is null
     */
    boolean removeFromStation(Song song, Station station) throws IllegalArgumentException, NullPointerException;

    /**
     * Sets a user's rating for a song, as a number of stars from 1.0 to 5.0.
     * If either the user or the song is null, the method will throw a
     * NullPointerException. If the user has already rated the given song,
     * the method will overwrite that rating with the new one. If the given rating
     * is not a double between 1.0 to 5.0, the method will throw an
     * IllegalArgumentException. Additionally, the method will throw an
     * IllegalArgumentException if the song or station doesn't exist within the system.
     *
     * @param user user whose rating should be set
     * @param song song which the user's rating should be set
     * @param rating double representing user's rating of a song from 1-5
     * @throws IllegalArgumentException if rating isn't between 1-5, if the user
     * doesn't exist within the system, or if the station doesn't exist within the system
     * @throws NullPointerException if either the user or song is null
     */
    void rateSong(User user, Song song, double rating) throws IllegalArgumentException, NullPointerException;

    /**
     * Clears a user's rating on a song. If this user has rated this song and
     * the rating has not already been cleared, then the rating is cleared and
     * the state will appear as if the rating was never made. If there is no
     * such rating on record for the user and the given song, nothing will altered.
     * Should either the User or Song be a null, the method will throw a NullPointerException.
     * If either the user or song doesn't exist within the system, the method will
     * also throw an IllegalArgumentException.
     *
     * @param user user whose rating should be cleared
     * @param song song from which the user's rating should be cleared
     * @throws IllegalArgumentException if either the user or song doesn't exist
     * within the system
     * @throws NullPointerException if either the user or the song is null
     */
    void clearRating(User user, Song song) throws IllegalArgumentException, NullPointerException;

    /**
     * Predicts the rating a user will assign to a song that they have not yet
     * rated, as a number of stars from 1.0 to 5.0. Should either the user or song
     * be null, the method will throw a NullPointerException. If the given song has
     * already been rated, then the method will throw an IllegalArgumentException.
     *
     * @param user user whose ratings should be predicted
     * @param song song from which the user's rating should be predicted
     * @return double representing the rating as the number of stars from 1 to 5
     * @throws IllegalArgumentException if song has already been rated, the user does
     * not exist within the system, or there is not enough data to make a prediction
     * @throws NullPointerException if either the user or song is null
     */
    double predictRating(User user, Song song) throws IllegalArgumentException, NullPointerException;

    /**
     * Suggests a song for a user that they are predicted to like. Should the
     * given user be null, the method will throw a NullPointerException. If the
     * given user does not exist within the system, then the method will throw
     * an IllegalArgumentException. If a song suggestion cannot be made for the
     * user either because there is insufficient data on the user, or for any
     * other reason, then the method will return null.
     *
     * @param user user who should receive a song suggestion
     * @return Song suggested for the given user
     * @throws IllegalArgumentException if user does not exist within the system
     * @throws NullPointerException if user is null
     */
    Song suggestSong(User user) throws IllegalArgumentException, NullPointerException;

}

