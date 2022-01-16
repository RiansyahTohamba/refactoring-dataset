rebaseAndCommitProject

# tantangan
adanya nested class new RebaseAndCommit.IRebase() {})
jadi didalam kelas tersebut beberapa method harus di override lagi
karena harus implement interface yang sudah dibuat.
akhirnya banyak coupling yang muncul pada nested IRebase.

# apa bedanya diantara RebaseAndCommit.rebaseAndCommit berikut ?
## tmx
RebaseAndCommit.rebaseAndCommit(
		tmxPrepared, remoteRepositoryProvider, config.getProjectRootDir(),
        tmxPath, 
        new RebaseAndCommit.IRebase() {})

## glossary
RebaseAndCommit.rebaseAndCommit(
	glossaryPrepared, remoteRepositoryProvider, config.getProjectRootDir(), 
	glossaryPath,
    new RebaseAndCommit.IRebase() {})

## keduanya sdh disolve
RebaseAndCommit.rebaseAndCommit untuk tmx dan glossary di-extract dari method rebaseAndCommitProject. 

