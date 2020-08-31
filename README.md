# Gowatch

This project is an API alike YouTube API for Zup bootcamp, its build in Spring.  

## Dependencies  

- Java JDK 11
- Spring Boot DevTools
- Spring Web
- Lombok
- Spring Data JPA
- Junit
- Spring Model Mapper

## Utils  

[Download gowatch insomnia project](https://drive.google.com/file/d/13MA3fv7AmL5S72YjpRhslsP4YeFUylT3/view?usp=sharing)

[Gowatch Trello](https://trello.com/b/O9oYQT6C/gowatch-api)

## API  

### Channel  

#### Create Channel  

> /channel

```json
{
    "username":"Channel Name",
    "email":"channel_email@email.com",
    "password":"channel_password",
    "about":"it's about a channel",
    "location":"A location"
}
```

*Http method POST*  
Create a channel.  
Only email and password is obrigatory.  
*Username* param is the channel name.  

#### Consult One Channel  

> /channel/{channel_id}

*Http method GET*  
Make a query and return a json with channel data found by channel id.  
Necessary to pass channel id in path variable.  

#### Consult All Channels  

> /channel

*Http method GET*  
Make a query and return a json with all channels data.  

#### Update Channel  

> /channel

```json
{
    "id":"1",
    "username":"Channel Name",
    "email":"channel_email@email.com",
    "password":"channel_password",
    "about":"it's about a channel",
    "location":"A location"
}
```

*Http method PUT*  
Update channel attributes.  

#### Delete Channel  

> /channel/{channel_id}

*Http method DELETE*  
Delete channel and all channel videos/rating/subscribers/subscriptions.  

### Subscriptions  

#### Subscribe  

> /channel/subscribe

```json
{
    "channel": "channel_id",
    "subscribed": "another_channel_id"
}
```

*Http method POST*  
Toggle subscribe/unsubscribed.  
*Channel* param is the channel id wants to subscribe.  
*Subscribed* param is channel id is subscribed.  

#### Consult Subscriptions  

> /channel/subscribe/{channel_id}

*Http method GET*  
Return channels that is subscribe with all data in json.  

### Video

#### Stream Video

> /video/stream/FILE_NAME

*Http method GET*  
Stream a video in browser.  
__DON'T USE IN INSOMNIA RAW DATA PREVIEW MODE, CAUSE CRASH__  

#### Create Video

> /video

FIELDS      | TYPE
----------- | ----
channel_id  | text
title       | text
description | text
video       | file

*Http method POST*  
Create and upload a video.

#### Consult One Video

> /video/{video_id}

*Http method GET*  
Make a query and return a json with video data found by video id.  
_When used, views field is incremented._  

#### Consult All Videos  

> /video

*Http method GET*
Make a query and return a json with all channels data.  

#### Update Video

> /video

```json
{
    "id": "video_id",
    "title": "A video title",
    "description":"A video description"
}
```

*Http method PUT*
Update video attributes.  

#### Delete Video

> /video/{video_id}

*Http method DELETE*  
Delete a video and rating of video.  

### Rating

#### Rating

> /video/rating

```json
{
    "channel":"channel_id",
    "video":"video_id",
    "type":"rating type"
}
```

*Http method POST*  
Rating a video and change if already rated.  
The type is only *like* or *dislike*.

#### Delete Rating

> /video/rating

```json
{
    "channel":"channel_id",
    "video":"video_id"
}
```

*Http method DELETE*  
Delete rating from a video.  
