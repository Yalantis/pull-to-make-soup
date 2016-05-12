##PullToMakeSoup

Custom animated pull-to-refresh that can be easily added to RecyclerView

[![Yalantis](https://raw.githubusercontent.com/Yalantis/PullToMakeSoup/master/PullToMakeSoupDemo/Resouces/badge_dark.png)](https://yalantis.com/?utm_source=github)

Check this [article on our blog]() to know more details about animations implementation

Inspired by [this project on dribble](https://dribbble.com/shots/2074667-Recipe-Finder-v-2)


<img src="https://raw.githubusercontent.com/Yalantis/PullToMakeSoup/master/PullToMakeSoupDemo/Resouces/recipe-finder.gif" />

##Requirements

Min SDK version 16

##Installing with [Gradle](http://gradle.org/)

##Usage

Include the library as local library project.

Second include PullToRefreshView to your layout, with the app:type="soup"

```xml
<com.yalantis.pulltomakesoup.PullToRefreshView
    android:id="@+id/pull_to_refresh"
    app:type="soup"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:divider="@null"
        android:dividerHeight="0dp"
        android:fadingEdge="none"/>

</com.yalantis.pulltomakesoup.PullToRefreshView>
```

Next in your onCreate method refer to the View and setup OnRefreshListener.

```java
mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
    @Override
    public void onRefresh() {}
});
```

When you need to finish the refreshing call

```java
mPullToRefreshView.setRefreshing(false);
```

# Misc
If you need to change progress state:
```java
	mPullToRefreshView.setRefreshing(boolean isRefreshing)
```
#Compatibility
  
  * Android JELLY BEAN 4.1+

### Version: 1.0

  * Initial Build

#### Let us know!

We’d be really happy if you sent us links to your projects where you use our component. Just send an email to github@yalantis.com And do let us know if you have any questions or suggestion regarding the animation. 

P.S. We’re going to publish more awesomeness wrapped in code and a tutorial on how to make UI for Android (iOS) better than better. Stay tuned!

## License

    Copyright 2015, Yalantis

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.