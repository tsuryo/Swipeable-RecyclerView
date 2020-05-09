[![](https://jitpack.io/v/tsuryo/Swipeable-RecyclerView.svg)](https://jitpack.io/#tsuryo/Swipeable-RecyclerView)

# Swipeable-RecyclerView
Android library provides a simple to use Swipeable RecyclerView.

<img width="175" alt="SwipeableRecyclerView" src="https://user-images.githubusercontent.com/42518244/81484001-d52db880-924a-11ea-887c-d0e46f9a378a.gif">  <img width="175" alt="SwipeableRecyclerView" src="https://user-images.githubusercontent.com/42518244/81484010-e2e33e00-924a-11ea-9284-0e6d3188c6b3.gif">  <img width="175" alt="SwipeableRecyclerView" src="https://user-images.githubusercontent.com/42518244/81484109-b54ac480-924b-11ea-9812-057e94e0c264.gif">

### Prerequisites
Android 5.0+ API 21+
# Features

* Customizable texts, icons & backgrounds.
* Customizable text size, text color.
* One side/two sides swipe.
* Really simple to use, implement swipe listener.
* Set attributes using XML/Java.

# Usage
### Java
```Java
        SwipeableRecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mAdapter);

        rv.setListener(new SwipeLeftRightCallback.Listener() {
            @Override
            public void onSwipedLeft(int position) {
                mList.remove(position);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onSwipedRight(int position) {
                mList.remove(position);
                mAdapter.notifyDataSetChanged();
            }
        });
	
	/*
         * Additional attributes:
         * */
        rv.setRightBg(R.color.blue);
        rv.setRightImage(R.drawable.ic_v);
        rv.setRightText("Right Text");

        rv.setLeftBg(R.color.red);
        rv.setLeftImage(R.drawable.ic_trash);
        rv.setLeftText("Left Text");

        rv.setTextSize(62);
        rv.setTextColor(R.color.white);
```
### XML
```XML
    <!--all attributes-->
    <com.tsuryo.swipeablerv.SwipeableRecyclerView
        android:id="@+id/rv"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:leftBgColor="@color/colorAccent"
        app:leftImage="@drawable/ic_remove"
        app:leftText="Delete"
        app:rightBgColor="@color/blue"
        app:rightImage="@drawable/ic_check"
        app:rightText="Read"
        app:textColor="@android:color/white"
        app:textSize="20sp" />
    <!--no images/icons-->
    <com.tsuryo.swipeablerv.SwipeableRecyclerView
        android:id="@+id/rv"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:leftBgColor="@color/colorAccent"
        app:leftText="Delete"
        app:rightBgColor="@color/blue"
        app:rightText="Read"
        app:textColor="@android:color/white"
        app:textSize="20sp" />
    <!--no texts-->
    <com.tsuryo.swipeablerv.SwipeableRecyclerView
        android:id="@+id/rv"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:leftBgColor="@color/colorAccent"
        app:leftImage="@drawable/ic_remove"
        app:rightBgColor="@color/blue"
        app:rightImage="@drawable/ic_check"
        app:textColor="@android:color/white"
        app:textSize="20sp" />
    <!--one side left-->
    <com.tsuryo.swipeablerv.SwipeableRecyclerView
        android:id="@+id/rv"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:leftBgColor="@color/colorAccent"
        app:leftImage="@drawable/ic_trash"
        app:leftText="Delete"
        app:textColor="@android:color/white"
        app:textSize="20sp" />
    <!--one side right-->
    <com.tsuryo.swipeablerv.SwipeableRecyclerView
        android:id="@+id/rv"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:rightBgColor="@color/colorAccent"
        app:rightImage="@drawable/ic_trash"
        app:rightText="Delete"
        app:textColor="@android:color/white"
        app:textSize="20sp" />
    <!--mixed-->
    <com.tsuryo.swipeablerv.SwipeableRecyclerView
        android:id="@+id/rv"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:leftBgColor="@color/colorAccent"
        app:leftImage="@drawable/ic_trash"
        app:leftText="Delete"
        app:rightBgColor="@color/blue"
        app:rightImage="@drawable/ic_v"
        app:textColor="@android:color/white"
        app:textSize="20sp" />

```
### Installing

Add the JitPack repository to your build file.
Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Add the dependency
```
dependencies {
        implementation 'com.github.tsuryo:Swipeable-RecyclerView:v1.0'
}
```
