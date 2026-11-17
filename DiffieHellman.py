p = 23
g = 5

print("Diffie-Hellman Key Exchange")
print(f"p = {p}, g = {g}")

try:
    a = int(input("\nAlice private key: "))
    b = int(input("Bob private key: "))
except ValueError:
    print("keys need to be integers")
    raise SystemExit(1)

if a <= 0 or b <= 0:
    print("use positive private keys")
    raise SystemExit(1)

alice_pub = pow(g, a, p)
bob_pub = pow(g, b, p)

print("\nPublic keys:")
print("Alice:", alice_pub)
print("Bob:", bob_pub)

s_alice = pow(bob_pub, a, p)
s_bob = pow(alice_pub, b, p)

print("\nShared secret:")
print("Alice:", s_alice)
print("Bob:", s_bob)

if s_alice == s_bob:
    print("\nKey exchange worked, secrets match.")
else:
    print("\nMismatch, something went wrong.")
