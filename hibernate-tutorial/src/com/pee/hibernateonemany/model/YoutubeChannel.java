package com.pee.hibernateonemany.model;

import javax.persistence.*;

@Entity
@Table(name = "t1_youtube_channel")
public class YoutubeChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "youtube_id")
    private int youtubeId;
    @Column(name = "channel_name")
    private String channelName;
    @Column(name = "subscriber")
    private String subscriber;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    public YoutubeChannel() {
    }

    public YoutubeChannel(String channelName, String subscriber)
    {
        this.channelName = channelName;
        this.subscriber = subscriber;
    }
    public int getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(int youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public String toString() {
        return "YoutubeChannel{" +
                "youtubeId=" + youtubeId +
                ", channelName='" + channelName + '\'' +
                ", subscriber='" + subscriber + '\'' +
                ", person=" + person +
                '}';
    }
}
