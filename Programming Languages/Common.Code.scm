(define a 50)
(define b 40)
(define c 30)
(define d 20)
(define e 10)

(define diner1(list "diner1" a b c d e))
(define diner2(list "diner2" e a d b c))
(define diner3(list "diner3" c e d b a))
(define diner4(list "diner4" e d c b a))

(define (score diner mod scr)
	(cond ((< mod 0.2)scr)
              (else
		(set! scr (+ scr(* (car diner) mod)))
		(set! mod (- mod 0.2))
			(score (cdr diner) mod scr)
		)
	)
)

(define score1 (score (cdr diner1) 1 0))
(define score2 (score (cdr diner2) 1 0))
(define score3 (score (cdr diner3) 1 0))
(define score4 (score (cdr diner4) 1 0))

(define diner1s(list "diner1" score1))
(define diner2s(list "diner2" score2))	
(define diner3s(list "diner3" score3))
(define diner4s(list "diner4" score4))

(define diners(list diner1s diner2s diner3s diner4s))

(define (insertion_sort diners)
	(cond ((null? diners) diners)
			((null? (cdr diners)) diners)
			(else (insert (car diners) (insertion_sort (cdr diners))))))

(define (insert item diners)
	(cond ((null? diners)(list item))
	((<= (cadr item) (cadar diners))(cons item diners))
	(else(cons(car diners)(insert item (cdr diners))))))