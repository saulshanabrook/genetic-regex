(ns genetic.regex.t-utils
  (:require
    [clojure.test :refer :all]
    [midje.sweet :refer :all]
    [genetic.regex.utils :as utils]))

(facts "about `count-true`"
  (utils/count-true #{nil}) => 0
  (utils/count-true [nil nil]) => 0
  (utils/count-true #{"something"}) => 1
  (utils/count-true #{true}) => 1
  (utils/count-true [false false]) => 0
  (utils/count-true #{"something" nil}) => 1)

(facts "about `count-false`"
  (utils/count-false #{nil}) => 1
  (utils/count-false #{"something"}) => 0
  (utils/count-false #{"something" nil}) => 1)

(facts "about `match?`"
  (utils/match? "." "a") => truthy
  (utils/match? "." "aa") => nil)

(facts "about `count-match`"
  (utils/count-match "." []) => 0
  (utils/count-match "." ["a"]) => 1
  (utils/count-match "." ["aa" "aa"]) => 0
  (utils/count-match "." ["a" "b"]) => 2)

(facts "about `count-no-match`"
  (utils/count-no-match "." []) => 0
  (utils/count-no-match "." ["a"]) => 0
  (utils/count-no-match "." ["aa"]) => 1
  (utils/count-no-match "." ["a" "a"]) => 0
  (utils/count-no-match "." ["aa" "aa"]) => 2)

(facts "about `rand-nth-set`"
  (utils/rand-nth-set #{1}) => 1
  (utils/rand-nth-set #{"a"}) => "a"
  (utils/rand-nth-set #{1 2}) => (some-checker 1 2))

(facts "about `all-chars`"
  (fact "it returns all the characters"
    (utils/all-chars #{"ab" "cd"}) => (exactly #{\a \b \c \d})
    (utils/all-chars #{"ab"}) => (exactly #{\a \b})))

(facts "about `maybe-string`"
  (fact "`maybe-string` returns either the string or a blank"
    (utils/maybe-string "a") => (some-checker "a" ""))
  (fact "`maybe-string` returns the right"
    (utils/maybe-string "a") => (some-checker "a" ""))
