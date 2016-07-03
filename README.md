# CodingDojoKatas

Contains KataBankOCR implementation in Java from http://codingdojo.org/cgi-bin/index.pl?KataBankOCR with some tests using the test cases listed on the kata page.

On command line takes one argument, a file containing account numbers to-be-read. Produces a file containing interpreted (when possible) account numbers with appropriate flags if there's a problem with the account number:
  *ERR* when there is an erroneous account number that can't be interpreted by adding or removing a pipe or an underscore
  *ILL* when there is an error with the original pipe-underscore representation (referred to as "digit representation" in the code.
  *AMB* when there are multiple possible account numbers when either ERR or ILL flagged account number is corrected with adding or removing a pipe/underscore.
