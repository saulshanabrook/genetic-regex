(defproject genetic-regex "0.1.0-SNAPSHOT"
  :description "Evolve regex with selective matching"
  :url "https://github.com/saulshanabrook/genetic-regex"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [roul "0.2.0"]]
  :main ^:skip-aot genetic.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[midje "1.5.1"]]}})
