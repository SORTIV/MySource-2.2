#/bin/sh
file="./deploy.properties"

if [ -f "$file" ]
then
 
  while IFS='=' read -r key value
  do
    key=$(echo $key | tr '.' '_')
    eval ${key}=\${value}
  done < "$file"
  
else
  echo "$file not found."
fi

if [ -d "${cloudseer_server_path}" ]; then
echo " Deploying cloudseer module ${cloudseer_module_name}"

rsync -a ./libs/* ${cloudseer_server_path}/WEB-INF/lib/
echo Copied files from libs folder to ${cloudseer_server_path}/WEB-INF/lib/

echo cloudseer module deployed successfully

else
  echo ${cloudseer_server_path} Path is invalid
 
fi  