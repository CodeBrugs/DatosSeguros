import hashlib
import secrets
import bcrypt

def generate_salt():
    # Genera una sal aleatoria segura
    return secrets.token_bytes(16)

def generate_hash(password, salt):
    # Combina la contraseña con la sal y obtén el hash usando bcrypt
    hashed_password = bcrypt.hashpw(password.encode(), salt)
    return hashed_password

def store_hashed_password_and_salt_in_database(hashed_password, salt):
    # En un entorno real, aquí se implementaría la lógica para almacenar
    # el hash y la sal en la base de datos asociada al usuario.
    print("Hash de contraseña almacenado en la base de datos.")

def verify_login(entered_password, stored_hash, salt):
    # Verifica el inicio de sesión usando bcrypt
    entered_password = entered_password.encode()
    if bcrypt.checkpw(entered_password, stored_hash):
        print("Inicio de sesión exitoso.")
    else:
        print("Inicio de sesión fallido. Las contraseñas no coinciden.")

def main():
    # Ejemplo de generación y almacenamiento seguro de contraseñas

    # Paso 1: Generar una sal (salt) aleatoria
    salt = generate_salt()

    # Paso 2: Obtener la contraseña del usuario (simulación)
    user_password = "SecurePassword123"

    # Paso 3: Generar el hash de la contraseña usando bcrypt
    hashed_password = generate_hash(user_password, salt)

    # Paso 4: Almacenar el hash y la sal en la base de datos
    store_hashed_password_and_salt_in_database(hashed_password, salt)

    # Verificación del proceso de inicio de sesión
    entered_password = input("Ingrese la contraseña para iniciar sesión: ")
    verify_login(entered_password, hashed_password, salt)

if __name__ == "__main__":
    main()

