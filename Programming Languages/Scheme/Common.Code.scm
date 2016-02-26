; This project was part of our "Common Code" project in PL. 
; We set up a table, filled with Diners
; The object was to place the diners at the table based on their common food choices
; Each dish was given a score, and depending on how it was ranked by the diner,
; we would alter the score by  using a modifier. This would allow diners with the same dish preference to be seated closly together

;define the score for each food choice
(define a 50)
(define b 40)
(define c 30)
(define d 20)
(define e 10)

;define our diners with preferences
(define diner1(list "diner1" a b c d e))
(define diner2(list "diner2" e a d b c))
(define diner3(list "diner3" c e d b a))
(define diner4(list "diner4" e d c b a))

;set up our score modifier function
(define (score diner mod scr)
	(cond ((< mod 0.2)scr);check to see if our modifier is above 0.2 (0 should be min)
              (else
		(set! scr (+ scr(* (car diner) mod)));;multiply the current modifier with the dish in the first position of the diner list
		(set! mod (- mod 0.2));;decrease the modifier
			(score (cdr diner) mod scr);;call the function recursively on the remaining dishs until modifier hits zero
		)
	)
)

;;set up the diners scores
(define score1 (score (cdr diner1) 1 0))
(define score2 (score (cdr diner2) 1 0))
(define score3 (score (cdr diner3) 1 0))
(define score4 (score (cdr diner4) 1 0))

;;set up the diners scores again, we used this to make output easier
(define diner1s(list "diner1" score1))
(define diner2s(list "diner2" score2))	
(define diner3s(list "diner3" score3))
(define diner4s(list "diner4" score4))

;;add the diners with their respective scores to a list
(define diners(list diner1s diner2s diner3s diner4s))

;;sort the diners based on their scores
(define (insertion_sort diners)
	(cond ((null? diners) diners)
			((null? (cdr diners)) diners)
			(else (insert (car diners) (insertion_sort (cdr diners))))))

;;
(define (insert item diners)
	(cond ((null? diners)(list item))
	((<= (cadr item) (cadar diners))(cons item diners))
	(else(cons(car diners)(insert item (cdr diners))))))
	
	;;after all this, user calls diners and the order is output
