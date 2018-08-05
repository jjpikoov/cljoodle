(defproject cljoodle "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [com.cognitect/transit-cljs "0.8.243"]
                 [reagent "0.7.0" :exclusions [cljsjs/react cljsjs/react-dom cljsjs/react-dom-server cljsjs/create-react-class]]
                 [re-frame "0.10.5"]
                 [cljs-http "0.1.45"]]
  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-figwheel "0.5.14"]]
  :clean-targets ["target/" "index.ios.js" "index.android.js" #_($PLATFORM_CLEAN$)]
  :aliases {"prod-build" ^{:doc "Recompile code with prod profile."}
            ["do" "clean"
             ["with-profile" "prod" "cljsbuild" "once"]]
            "advanced-build" ^{:doc "Recompile code for production using :advanced compilation."}
            ["do" "clean"
             ["with-profile" "advanced" "cljsbuild" "once"]]}
  :jvm-opts ["-XX:+IgnoreUnrecognizedVMOptions" "--add-modules=java.xml.bind"]
  :profiles {:dev {:dependencies [[figwheel-sidecar "0.5.14"]                                  
                                  [cider/piggieback "0.3.6"]]
                   :source-paths ["src" "env/dev"]
                   :cljsbuild    {:builds [{:id           "android"
                                            :source-paths ["src" "env/dev"]
                                            :figwheel     true
                                            :compiler     {:output-to     "target/android/not-used.js"
                                                           :main          "env.android.main"
                                                           :output-dir    "target/android"
                                                           :optimizations :none
                                                           :closure-defines {"clairvoyant.core.devmode" true
                                                                             "goog.DEBUG" true}}}
                                           #_($DEV_PROFILES$)]}
                   :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}}
             :prod {:cljsbuild {:builds [{:id           "ios"
                                          :source-paths ["src" "env/prod"]
                                          :compiler     {:output-to     "index.ios.js"
                                                         :main          "env.ios.main"
                                                         :output-dir    "target/ios"
                                                         :static-fns    true
                                                         :optimize-constants true
                                                         :optimizations :simple
                                                         :closure-defines {"goog.DEBUG" false}}}
                                         {:id           "android"
                                          :source-paths ["src" "env/prod"]
                                          :compiler     {:output-to     "index.android.js"
                                                         :main          "env.android.main"
                                                         :output-dir    "target/android"
                                                         :static-fns    true
                                                         :optimize-constants true
                                                         :optimizations :simple
                                                         :source-map     true
                                                         :closure-defines {"goog.DEBUG" false}}}
                                         #_($PROD_PROFILES$)]}}
             :advanced {:dependencies [[react-native-externs "0.1.0"]]
                        :cljsbuild {:builds [{:id           "ios"
                                              :source-paths ["src" "env/prod"]
                                              :compiler     {:output-to     "index.ios.js"
                                                             :main          "env.ios.main"
                                                             :output-dir    "target/ios"
                                                             :static-fns    true
                                                             :optimize-constants true
                                                             :optimizations :advanced
                                                             :closure-defines {"goog.DEBUG" false}}}
                                             {:id           "android"
                                              :source-paths ["src" "env/prod"]
                                              :compiler     {:output-to     "index.android.js"
                                                             :main          "env.android.main"
                                                             :output-dir    "target/android"
                                                             :static-fns    true
                                                             :optimize-constants true
                                                             :optimizations :advanced
                                                             :closure-defines {"goog.DEBUG" false}}}
                                             #_($ADVANCED_PROFILES$)]}}})
