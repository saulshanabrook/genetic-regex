(ns genetic.regex.operations
  (:require
    [roul.random :as random]
    [genetic.contrib.regex.utils :as utils]))


(defn random-char
  [possible-chars]
  "Returns a character or the `.` symbol for the set of symbols given"
  (random/rand-nth-weighted
          [[(utils/rand-nth-set possible-chars) 1]
           [\. 1]]))

(defn random-string
  [possible-chars length]
  (apply str (repeatedly length #(random-char possible-chars))))

(defn new-part
  [possible-chars]
  "Returns a new regex substring made of `possible-chars` plus `.`, `^`, and
  `$`.

  Length of substring is gaussian dist. around 1.
  `^` at  beginning is 50%
  `$` at end is 50%"
  (let [length (random/rand-gaussian-int 2 5 Double/POSITIVE_INFINITY 1)
        prefix (random/rand-nth-weighted
          [[\^ 1]
           ["" 1]])
        suffix (random/rand-nth-weighted
          [[\$ 1]
           ["" 1]])]
        (str prefix (random-string possible-chars length) suffix)))

(defn mutate-add [possible-chars regex-split]
  (conj regex-split (new-part possible-chars)))

(defn mutate-delete [regex-split]
  (let [value-to-drop (utils/rand-nth-set regex-split)]
    (disj regex-split value-to-drop)))

(defn mutate-operation [possible-chars regex-split]
  (let [operations [(partial mutate-add regex-split)
                    mutate-delete]
        chosen-operation (rand-nth operations)]
    (chosen-operation regex-split)))
