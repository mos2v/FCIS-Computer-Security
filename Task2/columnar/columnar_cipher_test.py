import unittest
from columnar import ColumnarCipher


class ColumnarCipherTest(unittest.TestCase):
    
    def test_encryption_with_valid_key(self):
        cipher = ColumnarCipher()
        key = [1, 3, 4, 2, 5]
        encrypted_text = cipher.encrypt("COMPUTERSCIENCE", key)
        self.assertEqual("CTIPSCOEEMRNUCE", encrypted_text, "Encryption failed with key 13425.")
    
    def test_decryption_with_valid_key(self):
        cipher = ColumnarCipher()
        key = [1, 3, 4, 2, 5]
        decrypted_text = cipher.decrypt("CTIPSCOEEMRNUCE", key)
        self.assertEqual("COMPUTERSCIENCE", decrypted_text, "Decryption failed with key 13425.")
    
    def test_encryption_with_different_key(self):
        cipher = ColumnarCipher()
        key = [5, 4, 3, 2, 1]
        encrypted_text = cipher.encrypt("COMPUTERSCIENCE", key)
        self.assertNotEqual("CTIPSCOEEMRNUCE", encrypted_text, "Encryption with key 54321 should produce different output.")
    
    def test_decryption_with_different_key(self):
        cipher = ColumnarCipher()
        key = [5, 4, 3, 2, 1]
        decrypted_text = cipher.decrypt("CTIMRNPSEOEUCE", key)
        self.assertNotEqual("COMPUTERSCIENCE", decrypted_text, "Decryption should fail with incorrect key.")
    
    def test_key_analysis(self):
        cipher = ColumnarCipher()
        key = cipher.analyse("COMPUTERSCIENCE", "CTIPSCOEEMRNUCE")
        self.assertEqual([1, 3, 4, 2, 5], key, "Key analysis failed.")
    
    def test_encryption_with_padding(self):
        cipher = ColumnarCipher()
        key = [1, 2, 3, 4]
        encrypted_text = cipher.encrypt("DATASECURITY", key)
        self.assertEqual("DSRAEITCTAUY", encrypted_text, "Encryption with padding failed.")
    
    def test_decryption_with_padding(self):
        cipher = ColumnarCipher()
        key = [1, 2, 3, 4]
        decrypted_text = cipher.decrypt("DSRXAEITCTAUY", key)
        self.assertEqual("DATASECURITYX", decrypted_text, "Decryption with padding failed.")
    
    def test_decryption(self):
        cipher = ColumnarCipher()
        key = [1, 3, 2]
        decrypted_text = cipher.decrypt("CPEMTOUR", key)
        self.assertEqual("COMPUTER", decrypted_text, "Decryption failed with key 132.")
    
    def test_empty_string_encryption(self):
        cipher = ColumnarCipher()
        key = [1, 2, 3]
        self.assertEqual("", cipher.encrypt("", key), "Encryption of an empty string should return an empty string.")
    
    def test_empty_string_decryption(self):
        cipher = ColumnarCipher()
        key = [1, 2, 3]
        self.assertEqual("", cipher.decrypt("", key), "Decryption of an empty string should return an empty string.")


if __name__ == "__main__":
    unittest.main()