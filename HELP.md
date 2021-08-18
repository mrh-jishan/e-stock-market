swagger market api - http://localhost:8081/api/swagger-ui/

swagger stock api - http://localhost:8082/api/swagger-ui/

swagger auth api - http://localhost:8083/api/swagger-ui/

gateway - http://localhost:8080

EUREKA 
http://localhost:8761/


mvn goal -Ddockerfile.username=... -Ddockerfile.password=...


# build chart 

% helm install --dry-run --debug ./charts/stock-api --generate-name
% helm install --dry-run --debug ./charts/auth-api --generate-name
% helm install --dry-run --debug ./charts/market-api --generate-name

# get port 

export NODE_PORT=$(kubectl get --namespace default -o jsonpath="{.spec.ports[0].nodePort}" services stock-api-1629234977)
export NODE_IP=$(kubectl get nodes --namespace default -o jsonpath="{.items[0].status.addresses[0].address}")
echo http://$NODE_IP:$NODE_PORT

ipconfig getifaddr en0 

helm upgrade --install stock-api ./charts/stock-api --debug  

kubectl get events -w

helm upgrade --install web-ui ./charts/web-ui

k exec -it web-ui-89bbfd4bf-hgc89 -- apk --no-cache add curl 
 
 helm install --install web-ui ./charts/web-ui --debug  
 
 helm install --dry-run --debug ./charts/web-ui --generate-name