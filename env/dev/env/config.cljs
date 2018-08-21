(ns env.config)

(def localhost-from-emulator-ip "10.0.3.2")
(def android-ws-url (str "ws://"
                         localhost-from-emulator-ip
                         ":3449/figwheel-ws"))

(def figwheel-urls {
                    :ios     "ws://localhost:3449/figwheel-ws"
                    :android android-ws-url
                    })
