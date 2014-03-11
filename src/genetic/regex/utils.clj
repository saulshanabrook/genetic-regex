(ns genetic.regex.utils
  (:require
    [roul.random :as random]))

(defn split-regex [regex-string]
  (set (clojure.string/split regex-string #"\|")))

(defn join-regex [regex-collection]
  (clojure.string/join "|" regex-collection))

(defn count-true [collection]
  (count (filter identity collection)))

(defn count-false [collection]
  (count (filter #(not (identity %)) collection)))

(defn match? [regex-string string]
  (re-matches (re-pattern regex-string) string))

(defn count-match [regex-string collection]
  (count-true (map (partial match? regex-string) collection)))

(defn count-no-match [regex-string collection]
  (count-false (map (partial match? regex-string) collection)))

(defn rand-nth-set [s]
  (rand-nth (apply vector s)))

(defn all-chars
  [collection-of-strings]
  (apply clojure.set/union (map set collection-of-strings)))

(defn maybe-string
  "Return either string or a blank string, in proportion to the
   probability"
  ([s]
    (maybe-string s 1/2))
  ([s probability]
    (random/rand-nth-weighted
      [[s probability]
       ["" (- 1 probability)]])))
