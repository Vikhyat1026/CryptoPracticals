p, q = 61, 53
n = p * q
phi = (p - 1) * (q - 1)
e = 17
d = pow(e, -1, phi)
print(f"p = {p}, q = {q}, n = {n}")
print(f"Public key: ({e}, {n})")
print(f"Private key: ({d}, {n})")
raw = input(f"\nEnter number to encrypt (0 to {n - 1}): ").strip()
if not raw.isdigit():
    print("enter a non-negative integer")
    raise SystemExit(1)
msg = int(raw)
if msg >= n:
    print(f"message has to be smaller than {n}")
    raise SystemExit(1)
cipher = pow(msg, e, n)
plain = pow(cipher, d, n)
print("\nOriginal :", msg)
print("Encrypted:", cipher)
print("Decrypted:", plain)
