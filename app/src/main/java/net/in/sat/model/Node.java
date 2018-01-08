package net.in.sat.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Node implements Parcelable {
    Category category;
    ArrayList<Node> child;

    public Node(Category category, ArrayList<Node> child) {
        this.category = category;
        this.child = child;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<Node> getChild() {
        return child;
    }

    public void setChild(ArrayList<Node> child) {
        this.child = child;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.category, flags);
        dest.writeList(this.child);
    }

    protected Node(Parcel in) {
        this.category = in.readParcelable(Category.class.getClassLoader());
        this.child = new ArrayList<Node>();
        in.readList(this.child, Node.class.getClassLoader());
    }

    public static final Parcelable.Creator<Node> CREATOR = new Parcelable.Creator<Node>() {
        @Override
        public Node createFromParcel(Parcel source) {
            return new Node(source);
        }

        @Override
        public Node[] newArray(int size) {
            return new Node[size];
        }
    };
}