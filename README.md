# NaraeAsynchronous

"날개" 를 뜻하는 대한민국 순 한글말, 나래 와 비동기를 뜻하는 Asynchronous 를 합하였습니다.

이제 당신의 Thread에도 날개를 부여합니다(?) 

## 구성

이 저장소는 두 가지의 모듈로 구성되어 있습니다.

:app -> 본 라이브러리를 사용하기에 앞서 사용법을 확인하고 싶으신 경우, 샘플로 확인할 수 있습니다.

* NaraeTask, NaraeInterface 에 대한 예제 제공

:library -> 라이브러리 본체입니다.

## 사용법 

* **NaraeMain.java** - 콜백이 없고, 단순히 메인 스레드를 생성해야 될 때 사용합니다.

```NaraeMain naraeMain = new NaraeMain(Runnable runnable);
naraeMain.execute(); ```

* **NaraeAsync.java** - 콜백이 없고, 별도 스레드를 생성해야 될 때 사용합니다.

```NaraeAsync naraeAsync = new NaraeAsync(Runnable runnable, int poolSize, String taskType);
naraeAsync.execute();```

* poolsize: 풀 사이즈, 한번에 돌아갈 수 있는 스레드의 개수를 제한합니다. (기본 값: 기기의 코어 갯수 + 1)
* tasktype: 같은 풀이라도 다른 작업이란 것을 알려줄 수 있는 변수입니다. (기본 값: 랜덤 5글자 생성)

poolsize, tasktype의 경우 생략이 가능합니다.

* **NaraeTask.java** - 콜백이 있고, 별도 스레드를 생성해야 될 때 사용합니다.

```NaraeTask naraeTask = new NaraeTask(NaraeInterface naraeInterface, int poolsize);
naraeTask.execute();```

      public class NT implements NaraeInterface<String> {
        @Override
        public String doInBackground() {
            String generated = "";

            for (int i = 0; i < 100; i++) {
                generated = generated + " " + RandomAttributes.getRandomTaskType();
            }

            return generated;
        }

        @Override
        public void onPostExecute(String s) {
            TextAppend(s);
        }
    }
    
구현시 <> 에는 **Object** 를 상속하는 객체가 들어가야 합니다.
    
자세한 사항은 [예제 링크](https://github.com/WindSekirun/NaraeAsynchronous/blob/master/app/src/main/java/windsekirun/naraeasynchronous/demo/NaraeTaskTestActivity.java)를 참고하세요.

## 라이브러리 적용법

* 현재 수동배포밖에 지원하고 있지 않으나 maven repo 배포 예정에 있습니다. 

## Special Thanks

* NaraeTask 에서 작업 처리에 있어 많은 도움을 준 [IrenSekirun](http://github.com/irensekirun)
* 이 라이브러리를 제대로 완성시키겠다고 다짐을 하게 만들은 [Palette Team](http://twitter.com/palette_twit)
* 그리고 밤 늦게까지 코딩하는 데에 지치지 않고 달리게 해 준 [코카콜라 제로](https://twitter.com/WindSekirun/status/612536852147404800)
* 일의 소중함을 깨닳게 해준 [그 분...](https://lh3.googleusercontent.com/-XQ4DM6vy-5Q/VYcJGiQIFLI/AAAAAAAAABw/1FZDzRKVMYI/s960/KakaoTalk_Photo_2015-06-22-03-55-13.jpeg)

## 라이센스

이 라이브러리는 MIT License를 사용합니다. [라이센스 전문](https://github.com/WindSekirun/NaraeAsynchronous/blob/master/License.md)

## 변경점

**v 1.0.0** (2015.06.22)

* 첫 커밋
* 기존에 존재하던 NaraeAsync 저장소 삭제
