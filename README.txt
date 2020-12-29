In this file you should include:

Any information you think we should know about your submission
* Is there anything that doesn't work? Why?

* Is there anything that you did that you feel might be unclear? Explain it here.

A description of the creative portion of the assignment
* Describe your feature
In this assignment, we design two parts of creative portion.
1. Radio lists and top tracks in a specific radio
In addtion to tracks in Chart or searched by users and playlists, users also can see radiolist which shows current popular radios with radio name and its cover.
After clicking the specific radio, users can get tracks in a individual radio. Because Deezer only randomly shows parts of tracks in the radio one time, so users also can use the REFRESH button to get more tracks in this radio.
Like Home page, after clicking the specific radio, users can get a track detail and also can add this track to playlists and listen the preview verison of the track.

2.Play the preview version of the specific track
In trackInformation page, except for track details, such like track name, album, duration and so on, users also can play the preview version of the track which lasts 30S.
In any time, users can pause the track, and continue on where they paused by hitting the play button again.
The user can also stop the track, and after this, hitting play button will replay the track from the beginning.

* Why did you choose this feature?
1.Radio lists and top tracks in a specific radio
In other music platforms, except for typing keywords, viewing most popular chart and viewing playlists to search tracks, users also can check radios to pick tracks.
Unlike just searching popular tracks, since radios normally categorized by genres, users can pick tracks in genres they prefer.

2.Play the preview version of the specific track
We have the operation to add tracks to playlists, but users could hardly make a decision only with some information, such like cover,artist, duraion, rank and so on.
So the preview version of the track is helpful for users to pick songs they may like.

* How did you implement it?

1.Radio lists and top tracks in a specific radio
To get radio data, show them on views and add tracks to database, we need build:
  Radio entity, RadioRepository, RadioViewModel, RadioListFragment, RadioAdapter and ShowRadioActivity.
1) Request radio data using API
We need two API request, one is to get radio lists and another is to get tracks in specific radio.
When MainActivity launched, the first API request is sent.
When users click specific radio, they will navigate from RadioFragment to ShowRadioActivity with 'radioId' and in the same time send the second API request to get tracks.
Using Radio entity, RadioRepository and RadioViewModel to get data.
2) Show radio data and tracks on views
Using recyclerview with gridlayout to show radio lists in RadioListFragment and show tracks in ShowRadioActivity.
3) Show track details
Navigate to TrackInformationActivity and implement the API to get tracks. This API and operations has been written in TrackInformationActivity before.
2.Play the preview version of the specific track
We has the URL of the preview version of track in Track entity and store it in database.
In TrackInformation page, we create a MediaPlayer and use the URL to prepare songs.
Using three buttons to implement the function to start, pause or stop the track.



1. (10 Points) Creative portion 
2. (8 Points) The UI uses LiveData in multiple ViewModels as well as observers to update the UI dynamically by calling Repositories to make both REST and SQL queries. 
3. (8 Points) Users can search for tracks by both artist and track name using the Deezer API 
4. (8 Points) Search results and chart information is displayed in a GridView with images 
5. (8 Points) Users can view all of their playlists in a RecyclerRiew with descriptions of each playlist 
6. (7 Points) Users can view the contents of their playlist in a RecyclerView with track name, artist, playlist genre, time, and playlist rating. This is done by joining two tables together to avoid data redundancy 
7. (7 Points) Detailed information about a track including song name, artist, album, track position in album, track length, release date, and rank are present when a track is clicked 
8. (5 Points) Retrieves and displays information about charts with RetroFit and the Deezer API when the app loads
 9. (6 Points) Users can create and store multiple playlists 
10. (6 Points) Users can add songs to playlist 
11. (5 Points) Users can delete songs from a playlist 
12. (5 Points) A tab view is used to toggle between Home and Playlist Views 
13. (5 Points) Uses coroutines to avoid blocking calls to the API or database 
14. (5 Points) The app does not crash and sanitizes inputs 
15. (4 Points) UI is ascetically pleasing and responsive across all devices. Selected text boxes should be visible while typing 
16. (3 Points) Code is well organized in folders and easy to read   

awesome job 100/100

