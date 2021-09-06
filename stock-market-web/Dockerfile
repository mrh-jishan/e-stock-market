FROM node:12.16-alpine AS build
WORKDIR /usr/src/app
COPY package.json package-lock.json ./
RUN npm install
RUN npm install -g @angular/cli
COPY . .

RUN ng build --prod


FROM nginx:1.16.1-alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /usr/src/app/dist/stock-market-web /usr/share/nginx/html
EXPOSE 80
