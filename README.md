# cljoodle

Diploma thesis project 

## Usage

### To run project:

```sh
npm install -g re-natal react-native-cli
npm install
re-natal use-android-device genymotion
re-natal use-figwheel
```

Previous command probably changed content of env/dev/env/config.cljs, please restore it, then:

```sh
cp index.android.js index.js
react-native upgrade # everywhere type n (no).
lein figwheel # in seperate terminal
npm start -- --reset-cache # in seperate terminal
```
Lunch Genymotion emulation & React Native Debugger

```sh
react-native run-android
```

