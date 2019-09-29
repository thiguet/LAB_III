package dao;

import java.io.IOException;
import java.util.List;

import main.Config;
import model.HashObj;
import util.StringHash;

public class HashTableDAO extends RAFDAO<StringHash> {
	private final int STRING_SIZE = 60;
	
	public HashTableDAO(String path) throws Exception {
		super(path);
		this.regsBytesSize = this.STRING_SIZE + 
							(Integer.SIZE / 8);
	}

	@Override
	public void appendData(StringHash data) {
		try {
			List<HashObj> hashObjs = data.getAll();
			this.openFile();
			
			hashObjs.stream()
					.forEach( h -> {
						try {
							int pos = this.headerBytesSize + (this.regsAmount * this.regsBytesSize);
							this.file.seek(pos);
							this.file.writeUTF(h.getStr());
							this.file.seek(pos + this.STRING_SIZE);
							this.file.writeInt(h.getFileId());							
							this.regsAmount++;
						} catch (IOException e) {
							e.printStackTrace();
						}					
					}
			);
			
			this.file.seek(0);
			this.file.writeInt(this.regsAmount);
			
			this.closeFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean setData(StringHash data, int key) {
		// Not Implemented yet
		return true;
	}

	@Override
	public StringHash getData(int key) {
		int pos = this.headerBytesSize + (0 * this.regsBytesSize);
		StringHash strHash = new StringHash(Config.HASH_PREFERRED_SIZE);
		try {
			HashObj aux = null;
			this.openFile();
			
			this.file.seek(pos);
			int i = this.regsAmount - 1;
			while(i > -1) {
				aux = new HashObj();
				pos = this.headerBytesSize + (i * this.regsBytesSize);
				
				this.file.seek(pos);
				aux.setStr(this.file.readUTF());
				
				this.file.seek(pos + this.STRING_SIZE);
				aux.setFileId(this.file.readInt());
				
				strHash.insert(aux);
				i--;
			}
			
			this.closeFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strHash;
	}

	@Override
	public List<StringHash> getAllData() {
		// Not Implemented yet
		return null;
	}

}
