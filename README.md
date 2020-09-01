# GenericResponseManager - Android Retrofit Generic Response

## Prerequisites

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

## Dependency

Add this to your module's `build.gradle` file (make sure the version matches the JitPack badge above):

```gradle
dependencies {
	...
		   implementation 'com.github.immujahidkhan:GenericResponseManager:v1.2'
		   implementation 'com.squareup.retrofit2:retrofit:2.7.2'
}
```


## Usage GET Request

``` java
 Map<String, String> mapString = new HashMap<>();
        mapString.put("user_id", "29");
        mapString.put("lang_id", "1");
        mapString.put("type", "passenger");
        mapString.put("device_token", "");
         new GenericResponseManager("http://Link/api/").postRequest(mapString, "logout", new onGenericResponseListener() {
            @Override
            public void onComplete() {
                Timber.tag(TAG).d("Complete");
                Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Response<?> response) {
                Timber.tag(TAG).d(String.valueOf(response.body()));
                String result = "" + response.body();
                Timber.tag(TAG).d(result);
                BaseModel baseModel = new Gson().fromJson(result, BaseModel.class);
                textView.setText("Ok : " + baseModel.getMessage());
            }

            @Override
            public void onErrorBody(ResponseBody response) {
                Timber.tag(TAG).d("Error %s", response);
            }
        });
```


## Usage POST Request

``` java
 Map<String, String> mapString = new HashMap<>();
        mapString.put("user_id", "29");
        mapString.put("lang_id", "1");
        mapString.put("type", "passenger");
        mapString.put("device_token", "");
        new GenericResponseManager("http://baseurl/api/").postRequest(mapString, "languages", new onGenericResponseListener() {
            @Override
            public void onComplete() {
                Timber.tag(TAG).d("Complete");
                Toast.makeText(MainActivity.this, "Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(String response) {
                LanguageSelectionModel srt = new Gson().fromJson(response, LanguageSelectionModel.class);
                Timber.tag(TAG).d(response);
                textView.setText(srt.getData().toString());
            }

            @Override
            public void onErrorBody(String response) {
                Timber.tag(TAG).d("Error %s", response);
            }
        });
```

