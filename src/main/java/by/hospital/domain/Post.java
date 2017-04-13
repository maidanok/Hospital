package by.hospital.domain;

import by.hospital.domain.enumeration.Gender;
import by.hospital.domain.enumeration.PostType;

/**
 * Created by Pasha on 12.04.2017.
 */
public class Post implements Entity<Integer> {
    private int postID;
    private PostType postName;

    @Override
    public Integer getPrimaryKey() {
        return postID;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        postID = primaryKey;
    }

    public String getPostName() {
        return postName.toString();
    }

    public void setPostName(String postName) {
        this.postName = PostType.valueOf(postName);
    }

    @Override
    public String toString() {
        return postName.getName();
    }
}
