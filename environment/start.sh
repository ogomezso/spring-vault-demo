docker compose up -d

VAULT_RETRIES=5
echo "Vault is starting..."
until docker compose exec vaul-server vault status > /dev/null 2>&1 || [ "$VAULT_RETRIES" -eq 0 ]; do
        echo "Waiting for vault to start...: $((VAULT_RETRIES--))"
        sleep 1
done

echo "Authenticating to vault..."
docker compose exec vault-server vault login token=vault-plaintext-root-token

echo "Initializing vault..."
docker compose exec vault-server vault secrets enable -path=kafka kv

echo "Adding policies for kafka..."
docker compose exec vault-server vault policy write kafka-policy /home/vault/config/kafkaPolicy.hcl

echo "Adding entries..."

docker compose exec vault-server vault kv put kafka/vault-demo credentials.user=test_user credentials.password=test_password

echo "Complete..."