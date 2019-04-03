FROM node:8-slim
MAINTAINER "thundyia@tcd.ie"

WORKDIR /root
COPY ./basicGUI/ .


RUN npm install
RUN npm install -g ionic cordova
EXPOSE 8100
CMD ["sh", "run.sh"]
