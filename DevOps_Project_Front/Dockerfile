# Étape de construction
FROM 18.12.0 as build-step
WORKDIR /app
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build

# Étape de déploiement
FROM nginx:1.17.1-alpine
COPY --from=build-step /app/dist/monappangular /usr/share/nginx/html
