package net.in.sat.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Product implements Parcelable {
    /**
     * id : 1
     * name : Nike Sneakers
     * date_added : 2016-12-09T11:16:11.000Z
     * variants : [{"id":1,"color":"Blue","size":42,"price":1000},{"id":2,"color":"Red","size":42,"price":1000},{"id":3,"color":"Blue","size":44,"price":1200},{"id":4,"color":"Red","size":44,"price":1200}]
     * tax : {"name":"VAT","value":12.5}
     */

    private int id;
    private String name;
    private String date_added;
    private Tax tax;
    private List<Variant> variants;

    private int view_count;
    private int ordered_count;
    private int shared_count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    public static class Tax implements Parcelable {
        /**
         * name : VAT
         * value : 12.5
         */

        private String name;
        private double value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeDouble(this.value);
        }

        public Tax() {
        }

        protected Tax(Parcel in) {
            this.name = in.readString();
            this.value = in.readDouble();
        }

        public static final Parcelable.Creator<Tax> CREATOR = new Parcelable.Creator<Tax>() {
            @Override
            public Tax createFromParcel(Parcel source) {
                return new Tax(source);
            }

            @Override
            public Tax[] newArray(int size) {
                return new Tax[size];
            }
        };
    }

    public static class Variant implements Parcelable {
        /**
         * id : 1
         * color : Blue
         * size : 42
         * price : 1000
         */

        private int id;
        private String color;
        private int size;
        private int price;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.color);
            dest.writeInt(this.size);
            dest.writeInt(this.id);
            dest.writeInt(this.price);
        }

        public Variant() {
        }

        protected Variant(Parcel in) {
            this.color = in.readString();
            this.size = in.readInt();
            this.id = in.readInt();
            this.price = in.readInt();
        }

        public static final Parcelable.Creator<Variant> CREATOR = new Parcelable.Creator<Variant>() {
            @Override
            public Variant createFromParcel(Parcel source) {
                return new Variant(source);
            }

            @Override
            public Variant[] newArray(int size) {
                return new Variant[size];
            }
        };
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getOrdered_count() {
        return ordered_count;
    }

    public void setOrdered_count(int ordered_count) {
        this.ordered_count = ordered_count;
    }

    public int getShared_count() {
        return shared_count;
    }

    public void setShared_count(int shared_count) {
        this.shared_count = shared_count;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.date_added);
        dest.writeParcelable(this.tax, flags);
        dest.writeTypedList(this.variants);
        dest.writeInt(this.view_count);
        dest.writeInt(this.ordered_count);
        dest.writeInt(this.shared_count);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.date_added = in.readString();
        this.tax = in.readParcelable(Tax.class.getClassLoader());
        this.variants = in.createTypedArrayList(Variant.CREATOR);
        this.view_count = in.readInt();
        this.ordered_count = in.readInt();
        this.shared_count = in.readInt();
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}