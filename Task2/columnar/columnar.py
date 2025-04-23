class ColumnarCipher:
    def encrypt(self, plaintxt, key):
        cols = len(key)
        rows = int(len(plaintxt) / cols)
        k = 0
        matrix = []
        for i in range(rows):
            row = []
            for j in range(cols):
                row.append(plaintxt[k])
                k += 1
            
            matrix.append(row)
            
        encrypted = ""
        
        for a in range(cols):
            for i in range(rows):
                encrypted += matrix[i][key.index(a + 1)]
                    
        return encrypted
    
    def decrypt(self, ciphertxt, key):
        cols = len(key)
        N = len(ciphertxt)
        
        rows = (N + cols - 1) // cols

        total_cells = rows * cols
        short_cols = total_cells - N  # these many columns have rows-1 entries
        # Calculate height of each column by original column index
        heights = [rows if c < (cols - short_cols) else rows - 1 for c in range(cols)]
        # Prepare empty matrix
        matrix = [[''] * cols for _ in range(rows)]

        pos = 0
        for label in range(1, cols + 1):
            col_idx = key.index(label)
            h = heights[col_idx]
            segment = ciphertxt[pos:pos + h]
            pos += h
            for r in range(h):
                matrix[r][col_idx] = segment[r]


        plaintext = ''
        for r in range(rows):
            for c in range(cols):
                if matrix[r][c]:
                    plaintext += matrix[r][c]
        return plaintext

    def analyse(self, plaintext, ciphertext):

        N = len(plaintext)
        for cols in range(2, N):
            if N % cols != 0:
                continue
            rows = N // cols
            matrix = [list(plaintext[i * cols:(i + 1) * cols]) for i in range(rows)]
            segments = [ciphertext[i * rows:(i + 1) * rows] for i in range(cols)]
            key = [0] * cols
            valid = True
            for seg_idx, segment in enumerate(segments):
                found = False
                for c in range(cols):
                    col_str = ''.join(matrix[r][c] for r in range(rows))
                    if col_str == segment:
                        key[c] = seg_idx + 1
                        found = True
                        break
                if not found:
                    valid = False
                    break
            if valid and sorted(key) == list(range(1, cols + 1)):
                return key
        return []
               
                
                   
                
        
            