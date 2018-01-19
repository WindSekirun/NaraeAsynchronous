## Deprecated
Please use RichUtils.runAsync() in [RichUtilsKt](https://github.com/WindSekirun/RichUtilsKt), it interop with kotlin and java.


# NaraeAsynchronous
NaraeAsynchronous is Easy-Make Main/Background threading and optimized multi-threading working such as getting image from variable request.

I made this library cause AsyncTask's poolsize is too small in various environment, this can be make lack of my application.

## Meaning
NaraeAsynchronous is compound word between "나래"(Narae) which means "Wing" in traditional korean word and Asynchronous.

Now, I'll give you wings on your Thread!

## Features

### Generate Main Thread 

**cannot be use as a UI Thread, use runOnUIThread instead.**

````Java
NaraeMain naraeMain = new NaraeMain(new Runnable() {
    @Override
    public void run() {
         // TODO: Do something!
    }
});
naraeMain.execute();
````

Do you want simple-code?

````Java
new NaraeMain(new Runnable() {
    @Override
    public void run() {
         // TODO: Do something!
    }
}).execute();
````

NaraeMain class has one methods, ````execute()````.

### Generate Background Thread

````Java
NaraeAsync naraeAsync = new NaraeAsync(new Runnable() {
   @Override
   public void run() {
       // TODO: Do something!
   } 
});
naraeAsync.execute();
````

Do you want simple-code?

````Java
new NaraeAsync(new Runnable() {
    @Override
    public void run() {
        //TODO: Do something!
    }
}).execute();
````

This features has Compatibility code of AsyncTask.

````Java
new NaraeAsync(new Runnable() {
    @Override
    public void run() {
        //TODO: Do something!
    }
}).executeOnExecutor();
````

#### Constructor
* Runnable ````runnable```` : Compulsory Object, Runnable object to run
* int ````poolSize```` : Optional Object, Pool size to execute multi-threading. Default value provided.
  * Basic Mode: Core Count + 1;
  * AsyncTask compatibility mode: Available Core Count + 1;
* String ````taskType```` : Optional Object, Task type. Default value provided.

#### License
````NaraAsync```` Task have Compatibility code of AsyncTask.

````
 Copyright (C) 2008 The Android Open Source Project

 Licensed under the Apache License, Version 2.0 (the "License")
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
      http://www.apache.org/licenses/LICENSE-2.0
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
````

### Combining Background threading + Main threading
This features provide you to execute background thread and execute main thread after executing background thread.

````Java
NaraeTask naraeTask = new NaraeTask(new NaraeInterface() {
    @Override
    public Object doInBackground() {
        return null;
    }

    @Override
    public void onPostExecute(Object o) {

    }
});
naraeTask.execute();
````

also, you can use simple-code.

````Java
 new NaraeTask(new NaraeInterface() {
    @Override
    public Object doInBackground() {
        return null;
    }

    @Override
    public void onPostExecute(Object o) {

    }
}).execute();
````

also, you can give specific return type.

````Java
new NaraeTask(new NaraeInterface<String>() {
    @Override
    public String doInBackground() {
        return null;
    }

    @Override
    public void onPostExecute(String s) {

    }
}).execute();
````

#### Constructor
* Runnable ````runnable```` : Compulsory Object, Runnable object to run
* int ````poolSize```` : Optional Object, Pool size to execute multi-threading. Default value provided.
  * Basic Mode: Core Count + 1;
  * AsyncTask compatibility mode: Available Core Count + 1;

## Changes
**v 1.1.0** (2017. 01. 31)
* adding AsyncTask compatibility mode in NaraeAsync
* Language change: Korean -> English
* Refactoring some code
* Rewrite README.md

**v 1.0.0** (2015. 06. 22)
* Inital Commit

## Contacts
"pyxis.moe#gmail.com".replace("#", "@");

## License
Regards to my philosophy, All library disributed by MIT License, You can see License File in [here](https://github.com/PyxisDev/pyxisdev.github.io/blob/master/LICENSE)
