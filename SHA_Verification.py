import hashlib
def sha256_hex(data):
    return hashlib.sha256(data.encode("utf-8")).hexdigest()
orig = input("Original message: ")
h1 = sha256_hex(orig)
recv = input("Message to verify: ")
h2 = sha256_hex(recv)
print("\nOriginal hash:", h1)
print("Received hash:", h2)
if h1 == h2:
    print("Match - message wasn't tampered with.")
else:
    print("Mismatch - message was modified.")
