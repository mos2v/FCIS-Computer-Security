import unittest
from des import DES


class DESTest(unittest.TestCase):

    def test_encryption(self):
        des = DES()
        key = "133457799BBCDFF1"  # 64-bit key in hex
        plain_text = "0123456789ABCDEF"  # 64-bit block in hex
        expected_cipher_text = "85E813540F0AB405"  # Known output from DES example
        actual_cipher_text = des.encrypt(plain_text, key)
        self.assertEqual(expected_cipher_text.upper(), actual_cipher_text.upper())

    def test_decryption(self):
        des = DES()
        key = "133457799BBCDFF1"
        cipher_text = "85E813540F0AB405"
        expected_plain_text = "0123456789ABCDEF"
        actual_plain_text = des.decrypt(cipher_text, key)
        self.assertEqual(expected_plain_text.upper(), actual_plain_text.upper())

    def test_different_key(self):
        des = DES()
        wrong_key = "A1B2C3D4E5F60708"
        cipher_text = "85E813540F0AB405"
        result = des.decrypt(cipher_text, wrong_key)
        self.assertNotEqual("0123456789ABCDEF", result)

    def test_same_encryption_decryption(self):
        des = DES()
        key = "AABB09182736CCDD"
        plain_text = "1234567890ABCDEF"
        encrypted = des.encrypt(plain_text, key)
        decrypted = des.decrypt(encrypted, key)
        self.assertEqual(plain_text.upper(), decrypted.upper())


if __name__ == "__main__":
    unittest.main()