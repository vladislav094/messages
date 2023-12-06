#!/bin/bash
while ! curl -s http://localhost:8088/message | grep -q '"status":"UP"'; do
    sleep 1
done