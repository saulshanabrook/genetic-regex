(ns genetic.regex.core
  "Evolve a regex that matches one group and not another

  GP version of http://nbviewer.ipython.org/url/norvig.com/ipython/xkcd1313.ipynb"
  (:require
    [clojure.set]
    [roul.random :as random]
    [genetic.core :as genetic]
    [genetic.contrib.regex.data :as data]
    [genetic.contrib.regex.utils :as utils]
    [genetic.contrib.regex.operations :as operations]))


(defn new-individual
  [possible-chars]
  (fn
    []
    (utils/join-regex
      (let [number-parts (random/rand-gaussian-int 5 5 Double/POSITIVE_INFINITY 1)]
        (repeatedly number-parts #(operations/new-part possible-chars))))))

(defn mutate
  [possible-chars]
  (fn
    [regex-string]
    "Changes sub parts of a regex string randomly.

    Regex may only contain special characters '|',' '.', '^', and '$'
    as well as any of the characters in `possible-chars` sequence

    Will add, delete, or modify one of the possible options in the regex
    (designated by the '|')"
    (utils/join-regex
      (operations/mutate-operation possible-chars (utils/split-regex regex-string)))))

(defn fitness
  [yes no]
  (fn
    [regex-string]
    "Asses how well `regex-string` matches all of the elements in `yes` and
    none of the elements in `no`.

    Returns a ratio of (count matching yes + count not matching no / sum count of yes + no)"
    (/
      (+
        (utils/count-match regex-string yes)
        (utils/count-no-match regex-string no))
      (count (clojure.set/union yes no)))))

(defn evolve-regex
  [yes no]
  (let [all-chars (utils/all-chars (concat yes no))]
    (genetic/evolve :new-individual (new-individual all-chars) :mutate (mutate all-chars) :fitness (fitness (set yes) (set no)) :acceptable-fitness 1 :popsize 100)))

(defn -main [input-alias]
  (apply evolve-regex ((keyword input-alias) data/data)))
