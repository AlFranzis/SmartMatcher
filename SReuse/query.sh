#!/bin/bash
echo "openContainer TPropose.dbxml 
query '$1' 
print" > tmp.queries
./run.sh tmp.queries



